# BeReal Tech test
(Open with Android Studio Electric Eel)
See *BeReal_test_technique.zip* for original spec.

NOTE:- The app currently doesn't run. This is because the user "Noel" no longer has an image folder associated with it on the cloud so, whilst the app successfully logs in, it fails to fetch any data. The error is handled gracefully, however.

### Incorporates:

- Kotlin KTS build files
- Feature modules and thin app
- Feature-localised clean-code architecture enforced by build file dependency
- KMM domain and data modules further enforcing clean code architecture
- Coroutine multi-threading
- Jetpack Compose UI and navigation (one Activity in the app and no Fragments)
- Experimental Compose transition animations
- UDF MVI presentation architecture
- Hilt dependency injection (for manual DI alternative, see https://github.com/mylesbennett/BeReal-Tech-Test---Manual-DI)
- TDD unit-tests featuring MockK and Turbine

### TODO:

- Separate Use Case implementations from Data Layer. This was an architectural short-cut taken to expedite delivery and to hide the Repository interface from the Presentation Layer (It didn't need access to it).
