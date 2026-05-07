# Library Management System — Class Diagram

**Student:** Kian Dehghani
**Student ID:** STUDENT_ID

```mermaid
classDiagram
    direction LR

    class Reportable {
        <<interface>>
        +generateReport() String
    }

    class ItemStatus {
        <<enumeration>>
        BORROWED
        IN_STORE
        LOST
    }

    class Item {
        <<abstract>>
        -String id
        -String title
        -ItemStatus status
        +getId() String
        +getTitle() String
        +getStatus() ItemStatus
        +setStatus(ItemStatus) void
        +describe()* String
    }

    class Book {
        -String isbn
        -String author
        -String genre
        +getIsbn() String
        +getAuthor() String
        +getGenre() String
        +describe() String
    }

    class DVD {
        -String director
        -int durationMinutes
        +getDirector() String
        +getDuration() int
        +describe() String
    }

    class Magazine {
        -String publisher
        -int issueNumber
        +getPublisher() String
        +getIssueNumber() int
        +describe() String
    }

    class User {
        <<abstract>>
        -String userId
        -String name
        -List~Item~ borrowedItems
        +getUserId() String
        +getName() String
        +getBorrowedItems() List~Item~
        +canBorrow(Item)* boolean
        +borrow(Item) void
        +returnItem(Item) void
    }

    class Student {
        +canBorrow(Item) boolean
    }

    class Teacher {
        +canBorrow(Item) boolean
    }

    class Admin {
        +generateReport() String
        +backupToCSV() void
    }

    class Library {
        -List~Item~ items
        -List~User~ users
        +addItem(Item) void
        +addUser(User) void
        +borrowItem(User, String) void
        +returnItem(User, String) void
        +searchByTitle(String) Set~Item~
        +searchByAuthor(String) Set~Item~
        +searchRecursive(List~Item~, String) Set~Item~
        +searchStream(String) Set~Item~
        +generateReport() String
    }

    class CSVService {
        +loadItems(String) List~Item~
        +loadUsers(String) List~User~
        +saveItems(String, List~Item~) void
        +saveUsers(String, List~User~) void
    }

    class BorrowingService {
        -Queue~String~ borrowQueue
        -Stack~String~ returnHistory
        +processBorrow(User, Item) void
        +processReturn(User, Item) void
    }

    class SortingService~T~ {
        +sortBy(List~T~, Comparator~T~) List~T~
    }

    class Validation {
        +isValidISBN(String)$ boolean
        +isValidUserId(String)$ boolean
    }

    class Constants {
        +MAX_BOOKS_STUDENT$ int
        +MAX_ITEMS_TEACHER$ int
        +BOOKS_CSV_PATH$ String
        +USERS_CSV_PATH$ String
    }

    class LibraryException {
        +LibraryException(String)
    }

    class Main {
        +main(String[])$ void
    }

    Item <|-- Book
    Item <|-- DVD
    Item <|-- Magazine
    User <|-- Student
    User <|-- Teacher
    User <|-- Admin
    Reportable <|.. Admin
    Reportable <|.. Library
    Item --> ItemStatus
    User "1" o-- "*" Item : borrows
    Library "1" *-- "*" Item
    Library "1" *-- "*" User
    Library ..> CSVService
    Library ..> BorrowingService
    Library ..> SortingService
    Library ..> Validation
    Admin ..> CSVService
    BorrowingService ..> LibraryException
```

## Notes
- `Item` and `User` are abstract base classes.
- `Reportable` interface is implemented by `Admin` and `Library`.
- `SortingService<T>` is generic and supplies different sorting strategies via `Comparator<T>`.
- `BorrowingService` uses a `Queue` (pending borrows) and a `Stack` (return history); search results return a `Set` to dedupe copies.
- `LibraryException` is thrown on invalid borrow/return operations (limit exceeded, all copies out, etc.).
