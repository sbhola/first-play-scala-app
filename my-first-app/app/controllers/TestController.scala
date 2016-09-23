package controllers

import javax.inject._

import models._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


/**
  * Created by sbhola on 9/21/2016.
  */
@Singleton
class TestController @Inject()extends Controller{

  def testMethod = Action {
    Ok("hello world my first test method.")
  }

  def savePlace = Action(BodyParsers.parse.json) { request =>
    val placeResult = request.body.validate[Place]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      place => {
        Place.save(place)
        Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+place.name+"' saved.") ))
      }
    )
  }

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    )(Location.apply _)

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "location").read[Location]
    )(Place.apply _)

}
