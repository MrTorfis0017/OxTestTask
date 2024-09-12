
# Application Setup and Usage Guide

## Prerequisites

To successfully run the application, ensure you have the following:

- **Java** version 19 or higher.
- **Docker** and **WSL 2.0** for container support.
- **Gradle** 8.7.

## Getting Started

### Step 1: Launch Docker Containers

Start by launching all the Docker containers. Run the following command from the root folder where the `Docker.yml` file is located:

### Step 2: Run the Application

Navigate to `src/main/java/com/ox/user`, find the `UserApplication` class, and run it as the main application.

### Step 3: Access the Application

Once the steps are complete, the application will be available at:

```
http://localhost:9071/
```

## Testing the Application

### 1. WebSocket Testing

- Open the application in two different browsers.
- After registering and logging in, go to `Tasks -> Add Task`.
- Return to the `/tasks` page and click the "View Details" button to see task details.
- On the task edit page, try changing the task status (`workStatus`) or the task end date (`endDate`) and save the changes. You will see a real-time notification in the second browser showing the updated task status.

### 2. Redis Caching

- When visiting the **Clients** page (`/clients`) for the first time, there will be a 5-second delay. This simulates checking the cache.
- On subsequent requests, the client list will load instantly as data is retrieved from the cache.
- **Note**: Whenever clients, contacts, or tasks are added, updated, or deleted, the cache will be cleared. The next request will again simulate a 5-second delay.
