import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import java.security.KeyStore

object KeystoreAesGcm {

    private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private const val ALGORITHM = "AES/GCM/NoPadding"
    private const val TAG_LENGTH = 128  // bits
    private const val IV_LENGTH = 12    // bytes

    // ─── Key Generation ───────────────────────────────────────────────────────

    fun generateKey(alias: String) {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE_PROVIDER
        )
        val spec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .setUserAuthenticationRequired(false) // set true to require biometrics
            .build()

        keyGenerator.init(spec)
        keyGenerator.generateKey()
    }

    // ─── Key Retrieval ─────────────────────────────────────────────────────────

    fun getKey(alias: String): SecretKey {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply { load(null) }
        return keyStore.getKey(alias, null) as? SecretKey
            ?: run {
                generateKey(alias)
                keyStore.load(null)
                keyStore.getKey(alias, null) as SecretKey
            }
    }

    // ─── Encrypt ───────────────────────────────────────────────────────────────

    fun encrypt(plaintext: ByteArray, alias: String): ByteArray {
        val key = getKey(alias)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val iv = cipher.iv  // Keystore generates IV automatically
        val ciphertext = cipher.doFinal(plaintext)

        // Prepend IV to ciphertext: [12 bytes IV | ciphertext + 16 byte auth tag]
        return iv + ciphertext
    }

    // ─── Decrypt ───────────────────────────────────────────────────────────────

    fun decrypt(data: ByteArray, alias: String): ByteArray {
        val key = getKey(alias)

        val iv = data.copyOfRange(0, IV_LENGTH)
        val ciphertext = data.copyOfRange(IV_LENGTH, data.size)

        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(TAG_LENGTH, iv))

        return cipher.doFinal(ciphertext) // throws AEADBadTagException if tampered
    }
}