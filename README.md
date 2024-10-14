
# GitHub Activity Fetcher

A command-line application to fetch and display a GitHub user's recent activity. This project allows users to quickly view a GitHub user's contributions and interactions directly from the terminal.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Example Output](#example-output)
- [Technology Stack](#technology-stack)
- [How It Works](#how-it-works)
- [Example Output](#example-output)
- [Sample API Response](#sample-api-response)
- [Error Handling](#error-handling)
- [Contributing](#contributing)


## Features

- **Fetch Recent Activity**: Retrieve recent activities of any GitHub user.
- **Display Activities**: See various activities like pushes, opened issues, and starred repositories.
- **Command-Line Interface**: Operate in both interactive mode and command-line argument mode.
- **Color-Coded Output**: Enhanced readability with color-coded terminal output.
- **Error Handling**: Gracefully handle errors related to invalid usernames or API failures.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Maven**: For building and managing the project dependencies.

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/latecoder10/github-activity.git
1. **Navigate to the project directory**:

   ```bash
   cd github-activity
1. **Build the project using Maven**:

   ```bash
   mvn install

## Usage

### Running the Application
You can run the application in two ways:

1. **Command Line Argument**:
    You can run the application in two ways:
   
   ```bash
   mvn exec:java -Dexec.mainClass="com.github.activity.GitHubActivityFetcher" -Dexec.args="username"
Replace username with the GitHub username you want to check.

2. **Interactive Mode**:
    If no username is provided, the application will prompt you to enter a GitHub username.

## Example Output
   ```bash
   Pushed 3 commits to <user-name>/repo-name
   Opened a new issue in <user-name>/repo-name
   Starred <user-name>/repo-name
```

## Technology Stack
- **Java**: The programming language used for the application.
- **Maven**: For project management and dependency handling.
- **Apache HttpClient**: For making HTTP requests to the GitHub API.
- **JSON**: For parsing the API response data.
  
## How It Works
- **1.Constructing the API URL:** The application creates a URL to fetch user activity from the GitHub API.
- **2.Sending the Request:** It sends a GET request to the API and retrieves the data in JSON format.
- **3.Parsing JSON:** The application parses the JSON response to extract relevant activities.
- **4.Displaying Output:** It displays the activities in the terminal, using color coding for clarity.
## Sample API Response

When fetching the recent activity of a GitHub user, the API returns a JSON array of events. Here is a sample response for a user:

```json
[
  {
    "id": "1234567890",
    "type": "PushEvent",
    "actor": {
      "id": 1234567,
      "login": "latecoder10",
      "display_login": "latecoder10",
      "url": "https://api.github.com/users/latecoder10"
    },
    "repo": {
      "id": 12345678,
      "name": "latecoder10/sample-repo",
      "url": "https://api.github.com/repos/latecoder10/sample-repo"
    },
    "payload": {
      "push_id": 1234567890,
      "size": 1,
      "distinct_size": 1,
      "ref": "refs/heads/main",
      "head": "abcdef1234567890",
      "before": "1234567890abcdef",
      "commits": [
        {
          "sha": "abcdef1234567890",
          "author": {
            "email": "example@example.com",
            "name": "Ayan Pal"
          },
          "message": "Initial commit",
          "distinct": true,
          "url": "https://api.github.com/repos/latecoder10/sample-repo/commits/abcdef1234567890"
        }
      ]
    },
    "public": true,
    "created_at": "2024-10-14T12:34:56Z"
  },
  {
    "id": "1234567891",
    "type": "IssuesEvent",
    "actor": {
      "id": 1234567,
      "login": "latecoder10",
      "display_login": "latecoder10",
      "url": "https://api.github.com/users/latecoder10"
    },
    "repo": {
      "id": 12345678,
      "name": "latecoder10/sample-repo",
      "url": "https://api.github.com/repos/latecoder10/sample-repo"
    },
    "payload": {
      "action": "opened",
      "issue": {
        "url": "https://api.github.com/repos/latecoder10/sample-repo/issues/1",
        "number": 1,
        "title": "Sample Issue",
        "body": "This is a sample issue."
      }
    },
    "public": true,
    "created_at": "2024-10-14T12:30:00Z"
  }
]
```

### Explanation of the Sample Response

You can also add a brief explanation of the key elements in the sample response:


### Explanation of Key Elements

- **id**: Unique identifier for the event.
- **type**: Type of activity (e.g., `PushEvent`, `IssuesEvent`).
- **actor**: Information about the user who performed the action.
  - **login**: GitHub username.
- **repo**: Repository associated with the activity.
  - **name**: Full name of the repository (e.g., `latecoder10/sample-repo`).
- **payload**: Contains details specific to the event type.
  - For `PushEvent`, it includes the commits made.
  - For `IssuesEvent`, it includes details of the opened issue.
- **created_at**: Timestamp of when the event occurred.


## Error Handling
The application handles various errors, including:

- **Invalid Usernames:** If a username is not found, an error message will be displayed.
- **Network Issues:** Handle exceptions related to network connectivity.
- **API Rate Limiting:** Inform the user if they've exceeded the GitHub API rate limit.
- **HTTP Errors:** Gracefully display errors related to HTTP response statuses.
## Contributing
Contributions are welcome! If you have suggestions for improvements or new features:

1. Fork the repository.
2. Create a new branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature/YourFeature).
5. Open a pull request.
## Project URL

For more details about the GitHub User Activity project, visit: [GitHub User Activity Project](https://roadmap.sh/projects/github-user-activity)

