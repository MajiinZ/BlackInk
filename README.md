# Blackink 🔒

**Blackink** is a cross-platform encrypted chat application built with Kotlin Multiplatform (KMP) and Compose Multiplatform. It allows users to securely send and receive messages across Android, iOS, and Desktop — with the guarantee that **no plaintext ever leaves the device**.

---

## Security Model

Blackink uses a layered cryptographic approach to protect every message end-to-end.

### Android Keystore Integration
On Android, all cryptographic keys are generated and stored inside the **Android Keystore System** — a hardware-backed secure enclave that ensures:

- Keys are **never exposed to the application process** in plaintext
- Keys **cannot be extracted** from the device, even by root-level access
- All encryption and decryption operations are performed **inside the secure hardware** when available

### Encryption
Every message is encrypted using **AES-256-GCM** (Advanced Encryption Standard, Galois/Counter Mode) before it is ever transmitted:

| Property | Detail |
|---|---|
| Algorithm | AES-256 |
| Mode | GCM (Authenticated Encryption) |
| Padding | None (stream cipher mode) |
| IV/Nonce | 96-bit, randomly generated per message |
| Auth Tag | 128-bit, verified on every receive |

GCM provides both **confidentiality** (nobody can read the message) and **integrity** (any tampering is detected and rejected). If a message is modified in transit, decryption will fail and the message will be discarded.

### What This Means for Users
- ✅ Messages are encrypted **before** leaving the device
- ✅ Only the intended recipient can decrypt and read the message
- ✅ Any attempt to tamper with a message in transit is automatically detected
- ✅ Keys are protected by Android hardware on supported devices
- ✅ No plaintext is ever stored or transmitted

---

## Platform Support

| Platform | Status |
|---|---|
| Android | ✅ Supported (APK distributed directly to clients) |
| iOS | ✅ Supported |
| Desktop (JVM) | ✅ Supported (macOS, Windows, Linux) |

> **Android clients** receive a direct APK file — no Play Store required.

---

## Project Structure

```
blackink/
├── composeApp/
│   └── src/
│       ├── commonMain/       # Shared logic and UI for all platforms
│       ├── androidMain/      # Android-specific code (Keystore, crypto)
│       ├── iosMain/          # iOS-specific code (CoreCrypto if applicable)
│       └── jvmMain/          # Desktop (JVM) specific code
└── iosApp/                   # iOS app entry point (Xcode / SwiftUI)
```

- **`commonMain`** — Shared business logic, ViewModels, UI, and encryption interfaces that work across all platforms
- **`androidMain`** — Android Keystore integration, platform-specific crypto implementations
- **`iosMain`** — iOS platform implementations; Apple's CoreCrypto can be called here if needed
- **`jvmMain`** — Desktop-specific implementations for Windows, macOS, and Linux
- **`iosApp`** — Required iOS entry point; add any SwiftUI code here

---

## Building & Running

### Android (APK)

**macOS / Linux:**
```shell
./gradlew :composeApp:assembleDebug
```

**Windows:**
```shell
.\gradlew.bat :composeApp:assembleDebug
```

The APK will be output to `composeApp/build/outputs/apk/debug/`. This file is distributed directly to clients.

---

### Desktop (JVM)

**macOS / Linux:**
```shell
./gradlew :composeApp:run
```

**Windows:**
```shell
.\gradlew.bat :composeApp:run
```

You can also use the run configuration widget in Android Studio or IntelliJ IDEA.

---

### iOS

Open the [`/iosApp`](./iosApp) directory in **Xcode** and run from there, or use the run configuration widget in your IDE.

---

## Requirements

- **Android:** API 23+ (Android 6.0 Marshmallow or higher) — required for Android Keystore AES-GCM support
- **JDK:** 17+
- **Xcode:** Required for iOS builds
- **Android Studio** or **IntelliJ IDEA** with Kotlin Multiplatform plugin recommended

---

## Learn More

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Android Keystore System](https://developer.android.com/privacy-and-security/keystore)
- [AES-GCM Encryption on Android](https://developer.android.com/privacy-and-security/cryptography)
