package com.bridgelabz.new_API_Automation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Rest_Automaton_Testing_Test {

	String token;
	String userId;
	String playlistId = "6Z9CO4oLpx7KP3lDyS6CP9";
	String trackId = "1YSeBt82FA3TKFarDumyH9";

	@BeforeTest

	public void get_Token() {
		token = "Bearer BQDASRkfnK_hGDIWtNVqTswM9cpHL9ZhbxaQlUKHRGyDT2kYpZVnDA1AMJJKDjAoZKNLHeecWdPpo0TviGDAnTo48uOE2RK2xuNZaFqNgyzDc8sWdL4P9QHaGiLKGl7A6x_yLRvskPPQdSx6Okmff1HRrRInAoxc8j6VbfXGKbIVa9xtPUnIs4k0x62Th23XZ4R2QfNnkFx_xPHWvrgly3GUjTgFCFxeYAyWyNARKhy4HENA6nLu1LgydX9fvNxGhnN5X1-urdM";
	}

	@Test(priority = 1)

	public void get_User_Current_Profile() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
		System.out.println("Current User Id is: " + userId);
		Assert.assertEquals("317r5n3cnrbrww7tlxzyp337duqq", userId);

	}

	@Test(priority = 2)

	public void Create_Playlist() {

		Response response = RestAssured.given(
				).contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"GP19\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/" + userId + "/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		playlistId = response.path("id");
		System.out.println("My current playlist id is = " + playlistId);
	}

	@Test(priority = 3)

	public void search_For_Item() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q", "sachin kumavat").queryParam("type", "track")
				// .pathParam("q","sachin kumavat")
				// .pathParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)

	public void get_User_Profile() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.put("https://api.spotify.com/v1/users/317r5n3cnrbrww7tlxzyp337duqq");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
	}

	@Test(priority = 5)

	public void add_Item_To_Playlist() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("uris", "spotify:track:2lQSF6yJzwLZumJkPuePIc")
				.when()
				.post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);

	}

	@Test(priority = 6)

	public void change_Playlist_Details() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n" + "  \"name\": \"CQA 113 API\",\r\n"
						+ "  \"description\": \"Updated playlist description\",\r\n" + "  \"public\": false\r\n" + "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/6Z9CO4oLpx7KP3lDyS6CP9");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test(priority = 7)

	public void get_Playlist_Item() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test(priority = 8)

	public void Get_Playlist() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/" + playlistId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 9)

	public void Get_Users_Playlists() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/" + userId + "/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 10)
	public void remove_Playlist_Item() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
		response.prettyPrint();
	}

	@Test(priority = 11)

	public void update_Playlist_Iteam() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n" + "  \"range_start\": 1,\r\n" + "  \"insert_before\": 3,\r\n"
						+ "  \"range_length\": 2\r\n" + "}")
				.queryParam("uris", "spotify:track:4TpAvJEQT2Tj820BRKIucw")
				.when()
				.put("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}

	@Test(priority = 12)

	public void Get_Current_Users_Playlists() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test(priority = 13)

	public void Get_Playlist_Cover_Image() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/" + playlistId + "/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 14)
	public void Get_Tracks() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 15)

	public void Get_Tracks_Audio_Features() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids", "4XEtEmnRgmNtDH9SayLCRR")
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 16)

	public void Get_Several_Tracks() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids", "4XEtEmnRgmNtDH9SayLCRR")
				.when()
				.get(" https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 17)

	public void Get_Track_Audio_Features() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	@Test(priority = 18)
	public void Get_Tracks_Audio_Analysis() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get(" https://api.spotify.com/v1/audio-analysis/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
}
