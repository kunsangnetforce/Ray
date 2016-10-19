package com.netforce.ray.home;

/**
 * Created by John on 10/13/2016.
 */
public class Home
{

    public String ID;
    public  String post_date;
    public  String post_title;
    public  String guid;
    public  String meta_value;
    public  String category;


    public Home(String ID,String post_date,String post_title,String guid,String meta_value, String category)
    {
        this.ID = ID;
        this.post_date = post_date;
        this.post_title = post_title;
        this.guid = guid;
        this.meta_value = meta_value;
        this.category = category;
    }

   public  String getID(){

       return ID;
   }

    public  String getPost_date(){
        return post_date;

    }

    public String getGuid(){

        return guid;
    }

    public  String getMeta_value(){
        return  meta_value;
    }

    public String getCategory(){
        return  category;
    }

}
