package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dcfportfolio.owlflashcardsen_cnlevel1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
    private static CardDatabase INSTANCE;
    private static Context mContext;

    public static CardDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CardDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CardDatabase.class, context.getString(R.string.flashcarddatabasename))
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    mContext = context.getApplicationContext();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final CardDao mDao;

        private final int cat1 = 1;
        private final int cat2 = 2;
        private final int cat3 = 3;
        private final int cat4 = 4;
        private final int cat5 = 5;
        private final int cat6 = 6;
        private final int cat7 = 7;
        private final int cat8 = 8;
        private final int cat9 = 9;

        PopulateDbAsync(CardDatabase db){
            mDao = db.cardDao();
        }
/*

        //Cards to be Populated Strings Section
        String[] cardListStrings = {
            //Animals category = 1
                "Dog", "狗", "gǒu",
                "Cat", "猫", "māo",
                "Horse","马", "mǎ",
                "Chicken", "鸡","jī",
                "Fish","鱼","yú",
                "Bear", "熊","xióng",
                "Bird", "鸟","niǎo",
                "Shark", "鲨鱼","shā yú",
                "Snake", "蛇","shé",
                "Pig", "猪","zhū",
                "Lion", "狮子","shī zi",
                "Turkey", "火鸡","huǒ jī",
                "Wolf", "狼","láng",
                "Spider", "蜘蛛","zhī zhū",
                "Rabbit", "兔子","tù zǐ",
                "Duck", "鸭子", "yā zi",
                "Deer", "鹿","lù",
                "Cow", "牛","niú",
                "Monkey","猴子","Hóu zi",
                "Lobster", "龙虾","lóng xiā",
                "Ape", "猿", "yuán",
                "Pony", "小马", "xiǎo mǎ",
                "Eagle", "鹰","yīng",
                "Dolphin", "海豚","hǎi tún",
                "Bison", "野牛", "yě niú",
                "Tiger","老虎","lǎo hǔ",
                "Giraffe", "长颈鹿", "cháng jǐng lù",
                "Cheeta", "猎豹", "liè bào",
                "Octopus", "章鱼", "zhāng yú",
                "Squid", "乌贼", "wū zéi",
                "Crab", "螃蟹","páng xiè",

            //BodyParts category = 2
                "Head","头","tóu",
                "Mouth","口","kǒu",
                "Ear","耳","ěr",
                "Nose","鼻子","bí zi",
                "Eye", "眼睛","yǎn jīng",
                "Hair", "头发","tóu fà",
                "Shoulder", "肩","jiān",
                "Arm","臂膀","bì bǎng",
                "Elbow","手肘", "shǒu zhǒu",
                "Hand", "手", "shǒu",
                "Finger", "手指", "shǒu zhǐ",
                "Chest", "胸部","xiōng bù",
                "Waist", "腰部", "yāo bù",
                "Hip", "臀部", "tún bù",
                "Leg", "腿","tuǐ",
                "Knee", "膝盖", "xī gài",
                "Foot", "脚", "jiǎo",
                "Toe", "脚趾", "jiǎo zhǐ",
                "Belly Button", "肚脐", "dù qí",
                "Back", "背部", "bèi bù",
                "Neck", "颈部", "jǐng bù",

            //Foods category = 3
                "Hamburger","汉堡包","hàn bǎo bāo",
                "Hot Dog","热狗","rè gǒu",
                "French Fries","炸薯条","zhà shǔ tiáo",
                "Pizza","比萨","bǐ sà",
                "Soda","苏打","sū dǎ",
                "Chicken Nuggets","鸡块","jī kuài",
                "Ice Cream","冰淇淋","bīng qí lín",
                "Donut","甜甜圈","tián tián quān",
                "Potato Chip","薯片","shǔ piàn",
                "Rice","米饭","mǐ fàn",
                "Noodle","面条","miàn tiáo",
                "Tofu","豆腐","dòu fu",
                "Meat","肉","ròu",
                "Egg","蛋","dàn",
                "Eggplant","茄子","qié zi",
                "Mushroom","蘑菇","mó gū",
                "Onion","洋葱","yáng cōng",
                "Carrot","胡萝卜","hú luó bo",
                "Bamboo Shoot","笋","sǔn",
                "Garlic","大蒜","dà suàn",
                "Ginger","生姜","shēng jiāng",
                "Egg Roll","蛋卷","dàn juǎn",
                "Apple","苹果","píng guǒ",
                "Banana","香蕉","xiāng jiāo",
                "Melon","瓜","guā",
                "Orange","桔子","jú zi",
                "Lettuce","生菜","shēng cài",
                "Bread","面包","miàn bāo",
                "Butter","牛油","niú yóu",
                "Peach","桃子","táo zi",
                "Grape","葡萄","pú táo",
                "Cherry","樱桃","yīng táo",
                "Water","水","shuǐ",
                "Salt","盐","yán",
                "Pepper","胡椒","hú jiāo",

            //Numbers category = 4
                "0\nZero","零","líng",
                "1\nOne","一","yī",
                "2\nTwo","二","èr",
                "3\nThree","三","sān",
                "4\nFour","四","sì",
                "5\nFive","五","wǔ",
                "6\nSix","六","liù",
                "7\nSeven","七","qī",
                "8\nEight","八","bā",
                "9\nNine","九","jiǔ",
                "10\nTen","十","shí",
                "11\nEleven","十一","shí yī",
                "12\nTwelve","十二","shí' èr",
                "13\nThirteen","十三","shí sān",
                "14\nFourteen","十四","shí sì",
                "15\nFifteen","十五","shí wǔ",
                "16\nSixteen","十六","shí liù",
                "17\nSeventeen","十七","shí qī",
                "18\nEighteen","十八","shí bā",
                "19\nNineteen","十九","shí jiǔ",
                "20\nTwenty","二十","èr shí",
                "30\nThirty","三十","sān shí",
                "40\nFourty","四十","sì shí",
                "50\nFifty","五十","wǔ shí",
                "60\nSixty","六十","liù shí",
                "70\nSeventy","七十","qī shí",
                "80\nEighty","八十","bā shí",
                "90\nNinety","九十","jiǔ shí",
                "100\nOne Hundred","一百","yī bǎi",
                "1000\nOne Thousand","一千","yī qiān",
                "1000000\nOne Million","一百万","yī bǎi wàn",

            //Objects category = 5
                "Pen","笔","bǐ",
                "Pencil","铅笔","qiān bǐ",
                "Paper","纸","zhǐ",
                "Spoon","勺子","sháo zi",
                "Fork","叉子","chā zi",
                "Chopsticks","筷子","kuài zi",
                "Knife","刀","dāo",
                "Bowl","碗","wǎn",
                "Plate","盘子","pán zi",
                "Cup","杯子","bēi zi",
                "Table","桌子","zhuō zi",
                "Chair","椅子","yǐ zi",
                "Radio","收音机","shōu yīn jī",
                "Phone","电话","diàn huà",
                "Newspaper","报纸","bào zhǐ",
                "Watch","手表","shǒu biǎo",
                "Necklace","项链","xiàng liàn",
                "Shoes","鞋","xié",
                "Shirt","衬衫","chèn shān",
                "Pants","裤子","kù zi",
                "Dress","连衣裙","lián yī qún",
                "Skirt","短裙","duǎn qún",
                "Coat","外套","wài tào",
                "Hat","帽子","mào zi",
                "Bed","床","chuáng",
                "Couch","长椅","cháng yǐ",
                "Bag","袋子","dài zi",
                "Window","窗口","chuāng kǒu",
                "Door","门","mén",
                "Napkin","餐巾","cān jīn",
                "Flower","花","huā",
                "Toy","玩具","wán jù",
                "Letter","信","xìn",
                "Painting","绘画","huì huà",
                "Box","盒子","hé zi",
                "Sink","水槽","shuǐ cáo",
                "Television (TV)","电视","diàn shì",
                "Clock","时钟","shí zhōng",

            //People category = 6
                "Man","男人","nán rén",
                "Woman","女人","nǚ rén",
                "Child","儿童","ér tóng",
                "Boy","男孩","nán hái",
                "Girl","女孩","nǚ hái",
                "Student","学生","xué shēng",
                "Doctor","医生","yī shēng",
                "Police Officer","警察","jǐng chá",
                "Teacher","老师","lǎo shī",
                "Custodian","保管人","bǎo guǎn rén",
                "Salesman","推销员","tuī xiāo yuán",
                "Waiter","服务员","fú wù yuán",
                "Nurse","护士","hù shì",
                "Technician","技术员","jì shù yuán",
                "Boss","老板","lǎo bǎn",
                "Wife","妻子","qī zi",
                "Husband","丈夫","zhàng fū",
                "Daughter","女儿","nǚ' ér",
                "Son","儿子","ér zi",
                "Baby","宝宝","bǎobāo",
                "Delivery Driver","送货司机","sòng huò sī jī",
                "Friend","朋友","péng yǒu",
                "Neighbor","邻居","lín jū",
                "Stranger","陌生人","mò shēng rén",

            //Places category = 7
                "House","房子","fáng zi",
                "Bathroom","浴室","yù shì",
                "Grocery Store","杂货店","zá huò diàn",
                "Supermarket","超级市场","chāo jí shì chǎng",
                "Gas Station","加油站","jiā yóu zhàn",
                "Mall","购物中心","gòu wù zhōng xīn",
                "Skyscraper","摩天大楼","mó tiān dà lóu",
                "Apartment","公寓","gōng yù",
                "Store","商店","shāng diàn",
                "Train Station","火车站","huǒ chē zhàn",
                "Zoo","动物园","dòng wù yuán",
                "Aquarium","水族馆","shuǐ zú guǎn",
                "City Hall","市政厅","shì zhèng tīng",
                "Jail","监狱","jiān yù",
                "Stadium","体育场","tǐ yù chǎng",
                "Park","公园","gōng yuán",
                "Beach","海滩","hǎi tān",
                "Forest","森林","sēn lín",
                "Mountain","山","shān",
                "Ocean","海洋","hǎi yáng",
                "River","河","hé",
                "Lake","湖","hú",
                "Desert","沙漠","shā mò",
                "Kitchen","厨房","chú fáng",
                "Bedroom","卧室","wò shì",
                "School","学校","xué xiào",
                "Hospital","医院","yī yuàn",
                "Police Station","警察局","jǐng chá jú",
                "Office","办公室","bàn gōng shì",

            //Shapes and Colors category = 8
                "Black","黑色","hēi sè",
                "White","白色","bái sè",
                "Gray","灰色","huī sè",
                "Purple","紫色","zǐ sè",
                "Blue","蓝色","lán sè",
                "Green","绿色","lǜ sè",
                "Yellow","黄色","huáng sè",
                "Orange","橙色","chéng sè",
                "Red","红色","hóng sè",
                "Brown","棕色","zōng sè",
                "Circle","圆","yuán",
                "Triangle","三角形","sān jiǎo xíng",
                "Square","正方形","zhèng fāng xíng",
                "Star","星星","xīng xīng",
                "Oval","椭圆","tuǒ yuán",
                "Rectangle","长方形","cháng fāng xíng",
                "Cone","锥体","zhuī tǐ",
                "Polygon","多边形","duō biān xíng",
                "Hexagon","六边形","liù biān xíng",
                "Octagon","八边形","bā biān xíng",

            //Transportation category = 9
                "Airplane","飞机","fēi jī",
                "Helicopter","直升机","zhí shēng jī",
                "Jet","喷气式飞机","pēn qì shì fēi jī",
                "Balloon","气球","qì qiú",
                "Ship","船","chuán",
                "Bicycle","自行车","zì xíng chē",
                "Rollar Skates","溜冰鞋","liū bīng xié",
                "Skate Board","滑板","huá bǎn",
                "Car","汽车","qì chē",
                "Truck","卡车","kǎ chē",
                "Bus","公共汽车","gōng gòng qì chē",
                "Train","火车","huǒ chē",
                "Subway","地铁","dì tiě",
                "Taxi","出租车","chū zū chē",
                "Motorcycle","摩托车","mó tuō chē",
                "Skis","滑雪板","huá xuě bǎn"
        };

        //Cards to be Populated Ints Section
        int[] soundsAndCategories = {
            //cat1 Animals
                R.raw.dog_en,R.raw.dog_cn,cat1,
                R.raw.cat_en,R.raw.cat_cn,cat1,
                R.raw.horse_en,R.raw.horse_cn,cat1,
                R.raw.chiken_en,R.raw.chiken_cn,cat1,
                R.raw.fish_en,R.raw.fish_cn,cat1,
                R.raw.bear_en,R.raw.bear_cn,cat1,
                R.raw.bird_en,R.raw.bird_cn,cat1,
                R.raw.shark_en,R.raw.shark_cn,cat1,
                R.raw.snake_en,R.raw.snake_cn,cat1,
                R.raw.pig_en,R.raw.pig_cn,cat1,
                R.raw.lion_en,R.raw.lion_cn,cat1,
                R.raw.turky_en,R.raw.turky_cn,cat1,
                R.raw.wolf_en,R.raw.wolf_cn,cat1,
                R.raw.spider_en,R.raw.spider_cn,cat1,
                R.raw.rabbit_en,R.raw.rabbit_cn,cat1,
                R.raw.duck_en,R.raw.duck_cn,cat1,
                R.raw.deer_en,R.raw.deer_cn,cat1,
                R.raw.cow_en,R.raw.cow_cn,cat1,
                R.raw.monkey_en,R.raw.monkey_cn,cat1,
                R.raw.lobster_en,R.raw.lobster_cn,cat1,
                R.raw.ape_en,R.raw.ape_cn,cat1,
                R.raw.pony_en,R.raw.pony_cn,cat1,
                R.raw.eagle_en,R.raw.eagle_cn,cat1,
                R.raw.dolpin_en,R.raw.dolpin_cn,cat1,
                R.raw.bison_en,R.raw.bison_cn,cat1,
                R.raw.tiger_en,R.raw.tiger_cn,cat1,
                R.raw.giraffe_en,R.raw.giraffe_cn,cat1,
                R.raw.cheeta_en,R.raw.cheeta_cn,cat1,
                R.raw.octopus_en,R.raw.octopus_cn,cat1,
                R.raw.squid_en,R.raw.squid_cn,cat1,
                R.raw.crab_en,R.raw.crab_cn,cat1,

            //cat2 BodyParts
                R.raw.head_en,R.raw.head_cn,cat2,
                R.raw.mouth_en,R.raw.mouth_cn,cat2,
                R.raw.ear_en,R.raw.ear_cn,cat2,
                R.raw.nose_en,R.raw.nose_cn,cat2,
                R.raw.eye_en,R.raw.eye_cn,cat2,
                R.raw.hair_en,R.raw.hair_cn,cat2,
                R.raw.shoulder_en,R.raw.shoulder_cn,cat2,
                R.raw.arm_en,R.raw.arm_cn,cat2,
                R.raw.elbow_en,R.raw.elbow_cn,cat2,
                R.raw.hand_en,R.raw.hand_cn,cat2,
                R.raw.finger_en,R.raw.finger_cn,cat2,
                R.raw.chest_en,R.raw.chest_cn,cat2,
                R.raw.waist_en,R.raw.waist_cn,cat2,
                R.raw.hip_en,R.raw.hip_cn,cat2,
                R.raw.leg_en,R.raw.leg_cn,cat2,
                R.raw.knee_en,R.raw.knee_cn,cat2,
                R.raw.foot_en,R.raw.foot_cn,cat2,
                R.raw.toe_en,R.raw.toe_cn,cat2,
                R.raw.belly_button_en,R.raw.belly_button_cn,cat2,
                R.raw.back_en,R.raw.back_cn,cat2,
                R.raw.neck_en,R.raw.neck_cn,cat2,

            //cat3 Food
                R.raw.hamburger_en,R.raw.hamburger_cn,cat3,
                R.raw.hot_dog_en,R.raw.hot_dog_cn,cat3,
                R.raw.french_fries_en,R.raw.french_fries_cn,cat3,
                R.raw.pizza_en,R.raw.pizza_cn,cat3,
                R.raw.soda_en,R.raw.soda_cn,cat3,
                R.raw.chiken_nuggets_en,R.raw.chiken_nuggets_cn,cat3,
                R.raw.ice_cream_en,R.raw.ice_cream_cn,cat3,
                R.raw.donut_en,R.raw.donut_cn,cat3,
                R.raw.potato_chip_en,R.raw.potato_chip_cn,cat3,
                R.raw.rice_en,R.raw.rice_cn,cat3,
                R.raw.noodle_en,R.raw.noodle_cn,cat3,
                R.raw.tofu_en,R.raw.tofu_cn,cat3,
                R.raw.meat_en,R.raw.meat_cn,cat3,
                R.raw.egg_en,R.raw.egg_cn,cat3,
                R.raw.eggplant_en,R.raw.eggplant_cn,cat3,
                R.raw.mushroom_en,R.raw.mushroom_cn,cat3,
                R.raw.onion_en,R.raw.onion_cn,cat3,
                R.raw.carrot_en,R.raw.carrot_cn,cat3,
                R.raw.bamboo_shoot_en,R.raw.bamboo_shoot_cn,cat3,
                R.raw.garlic_en,R.raw.garlic_cn,cat3,
                R.raw.ginger_en,R.raw.ginger_cn,cat3,
                R.raw.egg_roll_en,R.raw.egg_roll_cn,cat3,
                R.raw.apple_en,R.raw.apple_cn,cat3,
                R.raw.banana_en,R.raw.banana_cn,cat3,
                R.raw.melon_en,R.raw.melon_cn,cat3,
                R.raw.orange_food_en,R.raw.orange_food_cn,cat3,
                R.raw.lettuce_en,R.raw.lettuce_cn,cat3,
                R.raw.bread_en,R.raw.bread_cn,cat3,
                R.raw.butter_en,R.raw.butter_cn,cat3,
                R.raw.peach_en,R.raw.peach_cn,cat3,
                R.raw.grape_en,R.raw.grape_cn,cat3,
                R.raw.cherry_en,R.raw.cherry_cn,cat3,
                R.raw.water_en,R.raw.water_cn,cat3,
                R.raw.salt_en,R.raw.salt_cn,cat3,
                R.raw.pepper_en,R.raw.pepper_cn,cat3,

            //cat4 Numbers
                R.raw.zero_en,R.raw.zero_cn,cat4,
                R.raw.one_en,R.raw.one_cn,cat4,
                R.raw.two_en,R.raw.two_cn,cat4,
                R.raw.three_en,R.raw.three_cn,cat4,
                R.raw.four_en,R.raw.four_cn,cat4,
                R.raw.five_en,R.raw.five_cn,cat4,
                R.raw.six_en,R.raw.six_cn,cat4,
                R.raw.seven_en,R.raw.seven_cn,cat4,
                R.raw.eight_en,R.raw.eight_cn,cat4,
                R.raw.nine_en,R.raw.nine_cn,cat4,
                R.raw.ten_en,R.raw.ten_cn,cat4,
                R.raw.eleven_en,R.raw.eleven_cn,cat4,
                R.raw.twelve_en,R.raw.twelve_cn,cat4,
                R.raw.thirteen_en,R.raw.thirteen_cn,cat4,
                R.raw.fourteen_en,R.raw.fourteen_cn,cat4,
                R.raw.fifteen_en,R.raw.fifteen_cn,cat4,
                R.raw.sixteen_en,R.raw.sixteen_cn,cat4,
                R.raw.seventeen_en,R.raw.seventeen_cn,cat4,
                R.raw.eighteen_en,R.raw.eighteen_cn,cat4,
                R.raw.nineteen_en,R.raw.nineteen_cn,cat4,
                R.raw.twenty_en,R.raw.twenty_cn,cat4,
                R.raw.thirty_en,R.raw.thirty_cn,cat4,
                R.raw.fourty_en,R.raw.fourty_cn,cat4,
                R.raw.fifty_en,R.raw.fifty_cn,cat4,
                R.raw.sixty_en,R.raw.sixty_cn,cat4,
                R.raw.seventy_en,R.raw.seventy_cn,cat4,
                R.raw.eighty_en,R.raw.eighty_cn,cat4,
                R.raw.ninety_en,R.raw.ninety_cn,cat4,
                R.raw.one_hundred_en,R.raw.one_hundred_cn,cat4,
                R.raw.one_thousand_en,R.raw.one_thousand_cn,cat4,
                R.raw.one_million_en,R.raw.one_million_cn,cat4,

                //cat5 Objects
                R.raw.pen_en,R.raw.pen_cn,cat5,
                R.raw.pencil_en,R.raw.pencil_cn,cat5,
                R.raw.paper_en,R.raw.paper_cn,cat5,
                R.raw.spoon_en,R.raw.spoon_cn,cat5,
                R.raw.fork_en,R.raw.fork_cn,cat5,
                R.raw.chopsticks_en,R.raw.chopsticks_cn,cat5,
                R.raw.knife_en,R.raw.knife_cn,cat5,
                R.raw.bowl_en,R.raw.bowl_cn,cat5,
                R.raw.plate_en,R.raw.plate_cn,cat5,
                R.raw.cup_en,R.raw.cup_cn,cat5,
                R.raw.table_en,R.raw.table_cn,cat5,
                R.raw.chair_en,R.raw.chair_cn,cat5,
                R.raw.radio_en,R.raw.radio_cn,cat5,
                R.raw.phone_en,R.raw.phone_cn,cat5,
                R.raw.newspaper_en,R.raw.newspaper_cn,cat5,
                R.raw.watch_en,R.raw.watch_cn,cat5,
                R.raw.necklace_en,R.raw.necklace_cn,cat5,
                R.raw.shoes_en,R.raw.shoes_cn,cat5,
                R.raw.shirt_en,R.raw.shirt_cn,cat5,
                R.raw.pants_en,R.raw.pants_cn,cat5,
                R.raw.dress_en,R.raw.dress_cn,cat5,
                R.raw.skirt_en,R.raw.skirt_cn,cat5,
                R.raw.coat_en,R.raw.coat_cn,cat5,
                R.raw.hat_en,R.raw.hat_cn,cat5,
                R.raw.bed_en,R.raw.bed_cn,cat5,
                R.raw.couch_en,R.raw.couch_cn,cat5,
                R.raw.bag_en,R.raw.bag_cn,cat5,
                R.raw.window_en,R.raw.window_cn,cat5,
                R.raw.door_en,R.raw.door_cn,cat5,
                R.raw.napkin_en,R.raw.napkin_cn,cat5,
                R.raw.flower_en,R.raw.flower_cn,cat5,
                R.raw.toy_en,R.raw.toy_cn,cat5,
                R.raw.letter_en,R.raw.letter_cn,cat5,
                R.raw.painting_en,R.raw.painting_cn,cat5,
                R.raw.box_en,R.raw.box_cn,cat5,
                R.raw.sink_en,R.raw.sink_cn,cat5,
                R.raw.television_en,R.raw.television_cn,cat5,
                R.raw.clock_en,R.raw.clock_cn,cat5,

                //cat6 People
                R.raw.man_en,R.raw.man_cn,cat6,
                R.raw.woman_en,R.raw.woman_cn,cat6,
                R.raw.child_en,R.raw.child_cn,cat6,
                R.raw.boy_en,R.raw.boy_cn,cat6,
                R.raw.girl_en,R.raw.girl_cn,cat6,
                R.raw.student_en,R.raw.student_cn,cat6,
                R.raw.doctor_en,R.raw.doctor_cn,cat6,
                R.raw.police_officer_en,R.raw.police_officer_cn,cat6,
                R.raw.teacher_en,R.raw.teacher_cn,cat6,
                R.raw.custodian_en,R.raw.custodian_cn,cat6,
                R.raw.salesman_en,R.raw.salesman_cn,cat6,
                R.raw.waiter_en,R.raw.waiter_cn,cat6,
                R.raw.nurse_en,R.raw.nurse_cn,cat6,
                R.raw.technician_en,R.raw.technician_cn,cat6,
                R.raw.boss_en,R.raw.boss_cn,cat6,
                R.raw.wife_en,R.raw.wife_cn,cat6,
                R.raw.husband_en,R.raw.husband_cn,cat6,
                R.raw.daughter_en,R.raw.daughter_cn,cat6,
                R.raw.son_en,R.raw.son_cn,cat6,
                R.raw.baby_en,R.raw.baby_cn,cat6,
                R.raw.delivery_driver_en,R.raw.delivery_driver_cn,cat6,
                R.raw.friend_en,R.raw.friend_cn,cat6,
                R.raw.neighbor_en,R.raw.neighbor_cn,cat6,
                R.raw.stranger_en,R.raw.stranger_cn,cat6,

                //cat7 Places
                R.raw.house_en,R.raw.house_cn,cat7,
                R.raw.bathroom_en,R.raw.bathroom_cn,cat7,
                R.raw.grocery_store_en,R.raw.grocery_store_cn,cat7,
                R.raw.supermarket_en,R.raw.supermarket_cn,cat7,
                R.raw.gas_station_en,R.raw.gas_station_cn,cat7,
                R.raw.mall_en,R.raw.mall_cn,cat7,
                R.raw.skyscraper_en,R.raw.skyscraper_cn,cat7,
                R.raw.apartment_en,R.raw.apartment_cn,cat7,
                R.raw.store_en,R.raw.store_cn,cat7,
                R.raw.train_station_en,R.raw.train_station_cn,cat7,
                R.raw.zoo_en,R.raw.zoo_cn,cat7,
                R.raw.aquarium_en,R.raw.aquarium_cn,cat7,
                R.raw.city_hall_en,R.raw.city_hall_cn,cat7,
                R.raw.jail_en,R.raw.jail_cn,cat7,
                R.raw.stadium_en,R.raw.stadium_cn,cat7,
                R.raw.park_en,R.raw.park_cn,cat7,
                R.raw.beach_en,R.raw.beach_cn,cat7,
                R.raw.forest_en,R.raw.forest_cn,cat7,
                R.raw.mountain_en,R.raw.mountain_cn,cat7,
                R.raw.ocean_en,R.raw.ocean_cn,cat7,
                R.raw.river_en,R.raw.river_cn,cat7,
                R.raw.lake_en,R.raw.lake_cn,cat7,
                R.raw.desert_en,R.raw.desert_cn,cat7,
                R.raw.kitchen_en,R.raw.kitchen_cn,cat7,
                R.raw.bedroom_en,R.raw.bedroom_cn,cat7,
                R.raw.school_en,R.raw.school_cn,cat7,
                R.raw.hospital_en,R.raw.hospital_cn,cat7,
                R.raw.police_station_en,R.raw.police_station_cn,cat7,
                R.raw.office_en,R.raw.office_cn,cat7,

                //cat8 Shapes and Colors
                R.raw.black_en,R.raw.black_cn,cat8,
                R.raw.white_en,R.raw.white_cn,cat8,
                R.raw.gray_en,R.raw.gray_cn,cat8,
                R.raw.purple_en,R.raw.purple_cn,cat8,
                R.raw.blue_en,R.raw.blue_cn,cat8,
                R.raw.green_en,R.raw.green_cn,cat8,
                R.raw.yellow_en,R.raw.yellow_cn,cat8,
                R.raw.orange_color_en,R.raw.orange_color_cn,cat8,
                R.raw.red_en,R.raw.red_cn,cat8,
                R.raw.brown_en,R.raw.brown_cn,cat8,
                R.raw.circle_en,R.raw.circle_cn,cat8,
                R.raw.triangle_en,R.raw.triangle_cn,cat8,
                R.raw.square_en,R.raw.square_cn,cat8,
                R.raw.star_en,R.raw.star_cn,cat8,
                R.raw.oval_en,R.raw.oval_cn,cat8,
                R.raw.rectangle_en,R.raw.rectangle_cn,cat8,
                R.raw.cone_en,R.raw.cone_cn,cat8,
                R.raw.polygon_en,R.raw.polygon_cn,cat8,
                R.raw.hexagon_en,R.raw.hexagon_cn,cat8,
                R.raw.octagon_en,R.raw.octagon_cn,cat8,

                //cat9 Transportation
                R.raw.airplane_en,R.raw.airplane_cn,cat9,
                R.raw.helicopter_en,R.raw.helicopter_cn,cat9,
                R.raw.jet_en,R.raw.jet_cn,cat9,
                R.raw.balloon_en,R.raw.balloon_cn,cat9,
                R.raw.ship_en,R.raw.ship_cn,cat9,
                R.raw.bicycle_en,R.raw.bicycle_cn,cat9,
                R.raw.rollar_skates_en,R.raw.rollar_skates_cn,cat9,
                R.raw.skate_board_en,R.raw.skate_board_cn,cat9,
                R.raw.car_en,R.raw.car_cn,cat9,
                R.raw.truck_en,R.raw.truck_cn,cat9,
                R.raw.bus_en,R.raw.bus_cn,cat9,
                R.raw.train_en,R.raw.train_cn,cat9,
                R.raw.subway_en,R.raw.subway_cn,cat9,
                R.raw.taxi_en,R.raw.taxi_cn,cat9,
                R.raw.motorcycle_en,R.raw.motorcycle_cn,cat9,
                R.raw.skis_en,R.raw.skis_cn,cat9
        };

*/

        @Override
        protected Void doInBackground(Void... voids) {
            //reset database to blank slate
            mDao.deleteAll();
            Context localContext = mContext.getApplicationContext();

            //if (mDao.getAnyCard().length < 1){
            //Insert Cards
            /*for (int i=0; i<cardListStrings.length; i=i+3){
                Card card = new Card(cardListStrings[i], cardListStrings[i+1], cardListStrings[i+2], soundsAndCategories[i], soundsAndCategories[i+1], soundsAndCategories[i+2]);
                mDao.insertCard(card);
            }*/
            //}
            loadJSONData(localContext);
            return null;
        }

        private void loadJSONData(Context context){
            Context localContext = context.getApplicationContext();
            String json = loadJSONString();
            try {
                if (json!= null) {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jArray = jsonObject.getJSONArray("cards");
                    for (int i = 0; i < jArray.length(); ++i) {
                        JSONObject object = jArray.getJSONObject(i);
                        String english = object.getString("English");// english
                        String chinese = object.getString("Chinese"); // chinese
                        String pinyin = object.getString("Pinyin"); // pinyin
                        String soundEnName = object.getString("SoundEn"); //sound english
                        String soundCnName = object.getString("SoundCn"); //sound chinese
                        int category = object.getInt("Category"); // category
                        Log.d("LOADING_NEW_CARDS", english+" "+chinese);
                        //get Resource Ids
                        int enResourceId = localContext.getResources().getIdentifier(soundEnName, "raw", localContext.getPackageName());
                        int cnResourceId = localContext.getResources().getIdentifier(soundCnName, "raw", localContext.getPackageName());
                        if (enResourceId == 0){
                            enResourceId = -1;
                        }
                        if (cnResourceId == 0){
                            cnResourceId = -1;
                        }
                        Card card = new Card(english, chinese, pinyin, enResourceId, cnResourceId, category);
                        mDao.insertCard(card);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private String loadJSONString(){
            String json = null;
            try {
                InputStream is = mContext.getResources().openRawResource(R.raw.card_json_data);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Log.d("JSONSTRING ", json);
            return json;
        }
    }
}
