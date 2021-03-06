package models

case class Hotel(id: String, name : String,geocode : GeoCode,address : Address, policies: Policy)

case class GeoCode(lat: Double, long: Double)

case class Address(line1: String, line2: String, city: String,
                   state: String, country: String, postalCode: String)


case class Policy(policyType: String, text: String)