package services

import models._


/**
  * Created by sbhola on 9/23/2016.
  */
class HotelsService {

  def printHotelDetails(hotel : Hotel) : Unit = {
    println("Hotel Name : " + hotel.name)
    println("Hotel Id : " + hotel.id)
    println("Hotel Address : " + hotel.address.line1 + " " + hotel.address.city)
    println("Hotel Geocodes : " + hotel.geocode.lat + " , " + hotel.geocode.long)
    println("Hotel Policies : " + hotel.policies(0).policyType + " , " + hotel.policies(0).text)
  }
}
