package com.github.activity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A command-line application to fetch and display a GitHub user's recent
 * activity.
 */
public class GitHubActivityFetcher {

	private static final String GITHUB_API_URL = "https://api.github.com/users/%s/events"; // API URL template

	/**
	 * Main method to start the application.
	 *
	 * @param args Command-line arguments. The first argument should be the GitHub
	 *             username.
	 */
	public static void main(String[] args) {
		String username;

		// Check if a username was provided as a command line argument
		if (args.length < 1) {
			// Prompt for username if not provided
			System.out.print(Color.YELLOW + "Please enter a GitHub username: " + Color.RESET);
			Scanner scanner = new Scanner(System.in);
			username = scanner.nextLine(); // Read user input
			scanner.close();
		} else {
			username = args[0]; // Use the provided username
		}

		// Fetch and display user activity
		fetchUserActivity(username);
	}

	/**
	 * Fetches user activity from the GitHub API and displays it.
	 *
	 * @param username The GitHub username for which to fetch activity.
	 */
	private static void fetchUserActivity(String username) {
		String url = String.format(GITHUB_API_URL, username); // Construct the API URL

		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(url); // Create a GET request
			HttpResponse response = client.execute(request); // Execute the request

			// Check if the response is successful
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuilder json = new StringBuilder();
				String line;

				// Read the response content
				while ((line = reader.readLine()) != null) {
					json.append(line);
				}

				// Parse and display the activity from the JSON response
				parseAndDisplayActivity(json.toString());
			} else {
				// Handle HTTP error response
				System.out.println(
						Color.RED + "Error fetching data: " + response.getStatusLine().getStatusCode() + Color.RESET);
			}
		} catch (IOException e) {
			// Handle exceptions during the HTTP request
			System.out.println(Color.RED + "An error occurred: " + e.getMessage() + Color.RESET);
		}
	}

	/**
	 * Parses the JSON response containing user activity and displays it in the
	 * terminal.
	 *
	 * @param json The JSON string containing user activity data.
	 */
	private static void parseAndDisplayActivity(String json) {
		JSONArray events = new JSONArray(json); // Parse the JSON response into an array
		Set<String> seenMessages = new HashSet<>(); // To track seen messages

		System.out.println(Color.YELLOW + "Recent Activity:" + Color.RESET); // Header

		// Iterate through each event in the array
		for (int i = 0; i < events.length(); i++) {
			JSONObject event = events.getJSONObject(i); // Get the event object
			String type = event.getString("type"); // Get the event type
			String repo = event.getJSONObject("repo").getString("name"); // Get the repository name
			String message = ""; // Initialize message variable

			// Determine the message based on the event type
			switch (type) {
			case "PushEvent":
				int commits = event.getJSONObject("payload").getJSONArray("commits").length(); // Count commits
				message = String.format("Pushed %d commit(s) to %s", commits, repo);
				break;
			case "IssuesEvent":
				message = String.format("Opened a new issue in %s", repo);
				break;
			case "WatchEvent":
				message = String.format("Starred %s", repo);
				break;
			// Additional event types can be added here
			}

			// Print the message if it's not empty and hasn't been seen before
			if (!message.isEmpty() && !seenMessages.contains(message)) {
				seenMessages.add(message); // Track the message
				System.out.println(getColoredMessage(message, type)); // Print colored message
			}
		}
	}

	/**
	 * Gets a colored message based on the event type.
	 *
	 * @param message The message to color.
	 * @param type    The event type.
	 * @return The colored message.
	 */
	private static String getColoredMessage(String message, String type) {
		switch (type) {
		case "PushEvent":
			return Color.GREEN + message + Color.RESET; // Green for pushes
		case "IssuesEvent":
			return Color.BLUE + message + Color.RESET; // Blue for issues
		case "WatchEvent":
			return Color.CYAN + message + Color.RESET; // Cyan for stars
		default:
			return message; // Default color
		}
	}
}
