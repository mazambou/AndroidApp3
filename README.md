# AndroidApp3

## City Treasure Hunt

City Treasure Hunt is a simple Android treasure hunt application developed with Kotlin and Jetpack Compose.

The app allows participants to visit 20 local businesses around the city, starting at City Hall.

Each location provides a clue that guides the participant to the next destination.

After visiting all 20 locations, the participant becomes eligible to enter a draw for a free vacation.

## Features

- City Hall as the starting location
- 20 sequential treasure hunt locations
- Clues that guide users to the next destination
- Progress tracking
- Locked future locations
- Current-location identification
- Completion tracking
- Vacation draw eligibility message
- Restart functionality
- Multi-screen navigation
- Simple state management using ViewModel
- Material 3 user interface

## Application Screens

The application mainly contains four screens:

### Home

The Home screen introduces the treasure hunt and allows the participant to start the game.

It displays:

- Application title
- City anniversary message
- Treasure hunt instructions
- Vacation prize information
- Start Treasure Hunt button

### Treasure Hunt

The Treasure Hunt screen displays the participant's current location.

The displayed information includes:

- Current stop number
- Business name
- Business address
- Clue for the next destination

The user can:

- Move to the next location
- View the current progress

The treasure hunt starts at City Hall and continues through all 20 locations.

### Progress

The Progress screen displays the participant's advancement through the treasure hunt.

Locations are displayed using:

- ✅ Completed location
- 📍 Current location
- 🔒 Locked future location

The screen also displays the total progress, for example:

```text
5 / 20 locations visited
```

## Treasure Hunt Locations

The application contains 20 locations:

1. City Hall
2. Coffee Shop
3. Public Library
4. Downtown Bakery
5. Fresh Market
6. Family Restaurant
7. Flower Shop
8. Bike Shop
9. Fashion Store
10. Book Store
11. Pizza Restaurant
12. Community Pharmacy
13. Sports Store
14. Music Store
15. Chocolate Shop
16. Pet Store
17. Ice Cream Shop
18. Gift Shop
19. City Museum
20. Travel Agency

## Project Structure

```text
com.example.androidapp3
│
├── MainActivity.kt
│
├── data
│   └── BusinessData.kt
│
├── model
│   └── Business.kt
│
├── navigation
│   └── AppNavigation.kt
│
├── screens
│   ├── HomeScreen.kt
│   ├── HuntScreen.kt
│   ├── ProgressScreen.kt
│   └── FinishScreen.kt
│
├── viewmodel
│   └── TreasureHuntViewModel.kt
│
└── ui.theme
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```
