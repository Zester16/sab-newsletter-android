package com.example.sabnewsletter.network.sabencosNLApi

import com.example.sabnewsletter.domain.SabencosNewsLetterDashCountDomain


//for json parsing
data class SabencosNewsetterJSONCountRoot(val date:String?,val data:List<SabencosDashNewsletterCount?>)
data class SabencosDashNewsletterCount(val name:String?,val total:String?)

fun List<SabencosDashNewsletterCount?>.toSabencosNewsDashDomain():List<SabencosNewsLetterDashCountDomain>{

    return this.map { SabencosNewsLetterDashCountDomain(name=it?.name, total = it?.total) }
}