# BlackInk Android

A standalone Android Studio starter inspired by the module architecture in
[MzBotanicals](https://github.com/MajiinZ/MzBotanicals/tree/fe7dd0b0fbc969bfafa3333881d333bed285ec2f).
Open **this `androidApp` directory** in Android Studio, rather than the repository root.
It has its own settings, version catalog, and Gradle wrapper, independent of the
existing Kotlin Multiplatform project in the parent directory.

## Run

Use Android Studio with JDK 17 or newer and Android SDK 36 installed. Allow Gradle
sync to complete, choose the `app` run configuration, and select an emulator or
device running Android 8.0 / API 26 or newer. Android Studio creates the local
SDK configuration automatically.

From this directory on Windows:

```powershell
.\gradlew.bat :app:assembleDebug :data:testDebugUnitTest :app:lintDebug
```

On macOS or Linux, use `./gradlew` with the same tasks.
APK: `app/build/outputs/apk/debug/app-debug.apk`.
Application ID: `com.mz.blackink`.

## Architecture

| Module | Responsibility | MzBotanicals reference |
| --- | --- | --- |
| `app` | Application startup and Android activity | `composeApp` Android entry point |
| `shared` | Domain model and shared Compose theme | `shared` models and UI resources |
| `data` | Repository contract and in-memory implementation | `data/domain` interfaces and repository implementations |
| `feature:home` | Compose screen and lifecycle ViewModel | Feature-specific screens and ViewModels |
| `navigation` | Compose navigation graph | `navigation/NavGraph` |
| `di` | Koin repository and ViewModel registrations | `di/KoinModule` |

Dependency flow:

```text
app -> di -> data -> shared
       |-> feature:home -> data + shared
app -> navigation -> feature:home
app -> shared
```

The screen collects a repository StateFlow through its ViewModel, with lifecycle
awareness. Koin binds the repository interface to its implementation and creates
the ViewModel. New draft actions update the repository and recompose the list.
Feature screens do not construct repositories themselves.

This project uses native Android Kotlin and Jetpack Compose. The reference's
multiplatform targets, Firebase setup, commerce features, and branding are not
required for this starter. Existing parent-project sources are preserved.

## Starter scope

The interface uses a Telegram-inspired blue toolbar, circular initial avatars,
compact conversation rows, unread badges, pinned indicators, a navigation drawer,
and incoming/outgoing message bubbles. It is an interpretation of familiar
Telegram patterns, not an exact reproduction of the linked Figma community file.

The home screen includes clearly labeled sample conversations. Search matches
names and message previews; the Unread and Groups tabs filter the list. Opening a
chat clears its unread badge. The compose button creates a named conversation,
and its message composer adds local messages and updates the list preview.
Saved Messages is available from the drawer. Android Back returns to the chat
list, closes search, or dismisses the drawer.

Messages and new conversations disappear when the process exits. There is no backend, account system,
message transport, persistence, or encryption in this starter. The original
repository's encrypted-messaging goals remain future work; this project does
not implement or claim those security properties.

Add new features under `feature/`, register their ViewModels in `di`, and add
destinations in `navigation`. Replace `InMemoryConversationRepository` with a
production implementation when its behavior and requirements are defined.
