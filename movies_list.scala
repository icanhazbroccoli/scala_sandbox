package io.whitebox.scala.sandbox.movies;

object Tutorial {

  case class Video(title: String, video_type: String, length: Int)
  case class User(name: String, id: String)
  case class Movie(name: String, id: String)

  def getUserById(id: String)= id match {
    case "1" => Some(User("Mike", "1"))
    case _ => None
  }

  def getFavoriteMovieForUser(user: User) = user match {
    case User(_, "1") => Some(Movie("Gigli", "101"))
    case _ => None
  }

  def getVideosForMovie(movie: Movie) = movie match {
    case Movie(_, "101") =>
      Some(Vector(Video("Interview With Cast", "interview", 480),
                  Video("Gigli", "feature", 7260)))
      case _ => None
  }

  def getFavoriteVideos(userId: String) =
    for {
      user <- getUserById(userId)
      favoriteMovie <- getFavoriteMovieForUser(user)
      favoriteVideos <- getVideosForMovie(favoriteMovie)
    } yield favoriteVideos

}
