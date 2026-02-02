# Haqdar (MVP)

Offline-first Android app that personalizes "benefits + actions" for Indian citizens.

## Build
- Open the project folder in Android Studio (Koala+ recommended)
- Sync Gradle
- Run the `app` configuration

## What works
- Onboarding profile
- Rules engine creates personalized actions
- Offline storage using Room
- Daily reminder via WorkManager

## Next steps
- Replace seed schemes with live budget/scheme updates
- DigiLocker / PAN / Aadhaar optional linking
- Multilingual content packs
- Real scheme application deep-links & document checklist

## CI
- GitHub Actions builds a Debug APK on every push and PR.

> Note: Kotlin 2.0+ requires the Compose Compiler Gradle plugin. This project includes it.
