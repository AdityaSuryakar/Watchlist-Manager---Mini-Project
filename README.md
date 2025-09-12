# Watchlist Manager

## Overview
The Watchlist Manager is a simple Java application that allows users to manage their watchlist items. Users can add, remove, and view items in their watchlist, making it easier to keep track of what they want to watch.

## Project Structure
```
watchlist-manager
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── watchlist
│                       ├── WatchlistManager.java
│                       ├── WatchlistItem.java
│                       └── Main.java
├── .project
├── .classpath
└── README.md
```

## Files Description
- **WatchlistManager.java**: Contains the `WatchlistManager` class that manages the watchlist items. It includes methods to add, remove, and view items.
- **WatchlistItem.java**: Defines the `WatchlistItem` class, which represents an item in the watchlist with properties such as `id`, `title`, `type`, `status`, `rating`, and `userId`.
- **Main.java**: The entry point of the application, containing the `main` method to initialize the application and display the console menu.

## How to Run
1. Clone the repository or download the project files.
2. Open Eclipse and import the project as an existing project.
3. Navigate to the `Main.java` file.
4. Run the `Main` class to start the application.

## Future Enhancements
- Implement a persistent storage solution (e.g., database) to save watchlist items.
- Add a graphical user interface (GUI) for better user interaction.
- Include user authentication to manage personal watchlists.

## License
This project is licensed under the MIT License.