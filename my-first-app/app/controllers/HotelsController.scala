package controllers

import javax.inject._

import models._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


@Singleton
class HotelsController @Inject()extends Controller{

  def applyMarkups = Action(BodyParsers.parse.json){ request =>
    println(request.body)
    val hotelResult = request.body.validate[Hotel]
    hotelResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      hotel => {
        Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+hotel.name+"' saved.") ))
      }
    )
  }


  implicit val geocodeReads: Reads[GeoCode] = (
    (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    )(GeoCode.apply _)

  implicit val addressReads: Reads[Address]= (
  (JsPath \ "line1").read[String] and
    (JsPath \ "line2").read [String] and
    (JsPath \ "city").read[String] and
    (JsPath \ "state").read[String] and
    (JsPath \ "country").read[String]   and
    (JsPath \ "postalcode").read[String]
  )(Address.apply _)

  implicit val policyReads: Reads[Policy] = (
  (JsPath \ "policyType").read[String] and
    (JsPath \ "text").read[String]
  )(Policy.apply _)

  implicit val hotelReads:Reads[Hotel] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "geocode").read[GeoCode] and
      (JsPath \ "address").read[Address] and
      (JsPath \ "policies").read[Policy]
    )(Hotel.apply _)

}
