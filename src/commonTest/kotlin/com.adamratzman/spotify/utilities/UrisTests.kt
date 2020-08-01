/* Spotify Web API, Kotlin Wrapper; MIT License, 2017-2020; Original author: Adam Ratzman */
package com.adamratzman.spotify.utilities

import com.adamratzman.spotify.models.AlbumUri
import com.adamratzman.spotify.models.ArtistUri
import com.adamratzman.spotify.models.LocalTrackUri
import com.adamratzman.spotify.models.PlayableUri
import com.adamratzman.spotify.models.PlaylistUri
import com.adamratzman.spotify.models.SpotifyTrackUri
import com.adamratzman.spotify.models.SpotifyUri
import com.adamratzman.spotify.models.SpotifyUriException
import com.adamratzman.spotify.models.UserUri
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class UrisTests : Spek({
    describe("Uris tests") {
        describe("SpotifyTrackUri tests") {
            it("Create com.adamratzman.spotify track with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    SpotifyTrackUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    SpotifyTrackUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    SpotifyTrackUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                }
            }

            it("Create com.adamratzman.spotify track with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO",
                    SpotifyTrackUri("com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO").uri
                )

                assertEquals(
                    "1Z9UVqWuRJ7zToOiVnlXRO",
                    SpotifyTrackUri("com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO").id
                )

                assertEquals(
                    "com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO",
                    SpotifyTrackUri("1Z9UVqWuRJ7zToOiVnlXRO").uri
                )

                assertEquals(
                    "1Z9UVqWuRJ7zToOiVnlXRO",
                    SpotifyTrackUri("1Z9UVqWuRJ7zToOiVnlXRO").id
                )
            }
        }

        describe("LocalTrackUri tests") {
            it("Create local track with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    LocalTrackUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    LocalTrackUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    LocalTrackUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                }

                assertFailsWith<SpotifyUriException> {
                    LocalTrackUri("artist:album:name:id").uri
                }
            }

            it("Create local track with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:local:artist:album:name:id",
                    LocalTrackUri("com.adamratzman.spotify:local:artist:album:name:id").uri
                )

                assertEquals(
                    "artist:album:name:id",
                    LocalTrackUri("com.adamratzman.spotify:local:artist:album:name:id").id
                )
            }
        }

        describe("TrackUri tests") {
            it("Create track with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    PlayableUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    PlayableUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    PlayableUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                }
            }

            describe("Create any track with valid input") {
                it("Create remote track with uri") {
                    val trackUri = PlayableUri("com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO")
                    assertEquals(
                        SpotifyTrackUri::class,
                        trackUri::class
                    )
                    assertEquals(
                        "com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.uri
                    )
                    assertEquals(
                        "1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.id
                    )
                }
                it("Create local track with uri") {
                    val trackUri = PlayableUri("com.adamratzman.spotify:local:1Z9UVqWuRJ7zToOiVnlXRO")
                    assertEquals(
                        "com.adamratzman.spotify:local:1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.uri
                    )
                    assertEquals(
                        LocalTrackUri::class,
                        trackUri::class
                    )
                    assertEquals(
                        "1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.id
                    )
                }
                it("Create remote track with id") {
                    val trackUri = PlayableUri("1Z9UVqWuRJ7zToOiVnlXRO")
                    assertEquals(
                        SpotifyTrackUri::class,
                        trackUri::class
                    )
                    assertEquals(
                        "com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.uri
                    )
                    assertEquals(
                        "1Z9UVqWuRJ7zToOiVnlXRO",
                        trackUri.id
                    )
                }
            }
        }

        describe("UserUri") {
            it("Create user with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    UserUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    UserUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    UserUri("a:invalid").id
                }

                assertFailsWith<SpotifyUriException> {
                    UserUri("com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO").uri
                }
            }

            it("Create user with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                )

                assertEquals(
                    "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                    SpotifyUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                )

                assertEquals(
                    UserUri::class,
                    SpotifyUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83")::class
                )

                assertEquals(
                    "7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").id
                )

                assertEquals(
                    "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("7r7uq6qxa4ymx3wnjd9mm6i83").uri
                )

                assertEquals(
                    "7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("7r7uq6qxa4ymx3wnjd9mm6i83").id
                )

                assertEquals(
                    "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83:playlist:66wcLiS5R50akaQ3onDyZd").uri
                )

                assertEquals(
                    "7r7uq6qxa4ymx3wnjd9mm6i83",
                    UserUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83:playlist:66wcLiS5R50akaQ3onDyZd").id
                )

                assertEquals(
                    "com.adamratzman.spotify:user:",
                    UserUri("com.adamratzman.spotify:user:").uri
                )

                assertEquals(
                    "",
                    UserUri("com.adamratzman.spotify:user:").id
                )
            }
        }

        describe("PlaylistUri") {
            it("Create playlist with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    PlaylistUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    PlaylistUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    PlaylistUri("a:invalid").id
                }

                assertFailsWith<SpotifyUriException> {
                    PlaylistUri("com.adamratzman.spotify:track:1Z9UVqWuRJ7zToOiVnlXRO").uri
                }
            }

            it("Create playlist with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:playlist:66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("com.adamratzman.spotify:playlist:66wcLiS5R50akaQ3onDyZd").uri
                )

                assertEquals(
                    "66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("com.adamratzman.spotify:playlist:66wcLiS5R50akaQ3onDyZd").id
                )

                assertEquals(
                    "com.adamratzman.spotify:playlist:66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("66wcLiS5R50akaQ3onDyZd").uri
                )

                assertEquals(
                    "66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("66wcLiS5R50akaQ3onDyZd").id
                )

                assertEquals(
                    "com.adamratzman.spotify:playlist:66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83:playlist:66wcLiS5R50akaQ3onDyZd").uri
                )

                assertEquals(
                    "66wcLiS5R50akaQ3onDyZd",
                    PlaylistUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83:playlist:66wcLiS5R50akaQ3onDyZd").id
                )
            }
        }

        describe("AlbumUri tests") {
            it("Create album with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    AlbumUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    AlbumUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    AlbumUri("a:invalid").id
                }

                assertFailsWith<SpotifyUriException> {
                    AlbumUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                }
            }

            it("Create album with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:album:0W0ag2P4h1Fmp7PnGJVvIJ",
                    AlbumUri("com.adamratzman.spotify:album:0W0ag2P4h1Fmp7PnGJVvIJ").uri
                )

                assertEquals(
                    "0W0ag2P4h1Fmp7PnGJVvIJ",
                    AlbumUri("com.adamratzman.spotify:album:0W0ag2P4h1Fmp7PnGJVvIJ").id
                )

                assertEquals(
                    "com.adamratzman.spotify:album:0W0ag2P4h1Fmp7PnGJVvIJ",
                    AlbumUri("0W0ag2P4h1Fmp7PnGJVvIJ").uri
                )

                assertEquals(
                    "0W0ag2P4h1Fmp7PnGJVvIJ",
                    AlbumUri("0W0ag2P4h1Fmp7PnGJVvIJ").id
                )
            }
        }

        describe("ArtistUri tests") {
            it("Create artist with invalid input") {
                assertFailsWith<SpotifyUriException> {
                    ArtistUri("a:invalid")
                }

                assertFailsWith<SpotifyUriException> {
                    ArtistUri("a:invalid").uri
                }

                assertFailsWith<SpotifyUriException> {
                    ArtistUri("a:invalid").id
                }

                assertFailsWith<SpotifyUriException> {
                    ArtistUri("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83").uri
                }
            }

            it("Create artist with valid input") {
                assertEquals(
                    "com.adamratzman.spotify:artist:1XLjkBxFokuDTlHt0mQkRe",
                    ArtistUri("com.adamratzman.spotify:artist:1XLjkBxFokuDTlHt0mQkRe").uri
                )

                assertEquals(
                    "1XLjkBxFokuDTlHt0mQkRe",
                    ArtistUri("com.adamratzman.spotify:artist:1XLjkBxFokuDTlHt0mQkRe").id
                )

                assertEquals(
                    "com.adamratzman.spotify:artist:1XLjkBxFokuDTlHt0mQkRe",
                    ArtistUri("1XLjkBxFokuDTlHt0mQkRe").uri
                )

                assertEquals(
                    "1XLjkBxFokuDTlHt0mQkRe",
                    ArtistUri("1XLjkBxFokuDTlHt0mQkRe").id
                )
            }
        }
    }
    describe("Uri serialization test") {
        val json = Json(JsonConfiguration.Stable)
        it("create UserUri from json by using SpotifyUri.serializer()") {
            val spotifyUri: SpotifyUri =
                json.parse(SpotifyUri.serializer(), "\"com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83\"")
            assertEquals(
                UserUri::class,
                spotifyUri::class
            )
            assertEquals(
                "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                spotifyUri.uri
            )
        }
        it("create UserUri from json by using UserUri.serializer()") {
            val userUri = json.parse(UserUri.serializer(), "\"com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83\"")
            assertEquals(
                "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                userUri.uri
            )
        }
        it("try creating UserUri from json with id by using SpotifyUri.serializer()") {
            assertFailsWith<SpotifyUriException> {
                json.parse(SpotifyUri.serializer(), "\"7r7uq6qxa4ymx3wnjd9mm6i83\"")
            }
        }
        it("create UserUri from json with id by using UserUri.serializer()") {
            val userUri = json.parse(UserUri.serializer(), "\"7r7uq6qxa4ymx3wnjd9mm6i83\"")
            assertEquals(
                "com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83",
                userUri.uri
            )
        }
    }
    describe("Uri types test") {
        it("test user uri string is a UserUri") {
            assertTrue {
                SpotifyUri.isType<UserUri>("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83")
            }
        }
        it("test user id string is not a UserUri") {
            assertFalse {
                SpotifyUri.isType<UserUri>("7r7uq6qxa4ymx3wnjd9mm6i83")
            }
        }
        it("test user uri string can be a UserUri") {
            assertTrue {
                SpotifyUri.canBeType<UserUri>("com.adamratzman.spotify:user:7r7uq6qxa4ymx3wnjd9mm6i83")
            }
        }
        it("test user id string can't be a UserUri") {
            assertTrue {
                SpotifyUri.canBeType<UserUri>("7r7uq6qxa4ymx3wnjd9mm6i83")
            }
        }
    }
})
