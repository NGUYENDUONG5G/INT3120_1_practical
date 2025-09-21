package Unit2_Pathway1_Helloworld

import java.time.Year

class Song {
    private var title:String=""
    private var artist:String=""
    private var yearPublished:Int=0
    private var playCount:Int=0
    constructor(title:String,artist:String, yearPublished:Int,playCount:Int){
        this.title=title
        this.artist=artist
        this.yearPublished=yearPublished
        this.playCount=playCount
    }
    fun print(){
        "$title, performed by $artist, was released in $yearPublished."
    }
    fun isPopular():Boolean{
        return playCount>=1000
    }

}