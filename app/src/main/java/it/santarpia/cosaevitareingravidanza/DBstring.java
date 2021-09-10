package it.santarpia.cosaevitareingravidanza;

class DBstring {
    public static final String TBL_FOOD = "Foods";
    public static final String F_FOOD_ID = "_id";
    public static final String F_FOOD_NAME = "food_name";
    public static final String F_FOOD_TOXOPLASMOSI = "food_toxoplasmosi";
    public static final String F_FOOD_LISTERIOSI = "food_listeriosi";
    public static final String F_FOOD_DESCRIPTION = "food_description";
    public static final String F_FOOD_SAFE = "food_safe";
    public static final String F_FOOD_CATEGORY = "food_category";
    public static final String F_FOOD_TIME = "food_time";

    public static final String TBL_USER = "User";
    public static final String F_USER_NAME = "user_name";

    //Field that memorize version of database
    //Version changes everytime foods are added in database
    public static final String F_VERSION = "version";

    public static final String F_USER_TOXOPLASMOSI= "user_toxoplasmosi";
}
