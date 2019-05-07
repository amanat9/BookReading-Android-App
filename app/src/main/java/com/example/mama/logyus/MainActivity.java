package com.example.mama.logyus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String Book_Title;
    private String Category;
    private String Description;
    private int Thumbnail;

    Button searchButton;

    List<MainActivity> listBook;


    public MainActivity() {
    }


    public MainActivity(String title, String category, String description, int thumbnail) {
        Book_Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getBook_Title() {
        return Book_Title;
    }

    public String getCategory() { return Category; }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        searchButton = findViewById(R.id.buttonS);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Book_Search.class));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reading a good book is like taking a journey!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        listBook = new ArrayList<>();
        listBook.add(new MainActivity("A Thousand Splendid Stars","Category: Fiction","Description: A Thousand Splendid Suns is a breathtaking story set against the volatile events of Afghanistan's last thirty years—from the Soviet invasion to the reign of the Taliban to post-Taliban rebuilding—that puts the violence, fear, hope, and faith of this country in intimate, human terms. It is a tale of two generations of characters brought jarringly together by the tragic sweep of war, where personal lives—the struggle to survive, raise a family, find happiness—are inextricable from the history playing out around them.",R.drawable.athousandsplendidstars));
        listBook.add(new MainActivity("Murder On Orient Express","Category: Crime Fiction, Mystery, Detective Fiction","Description: Murder on the Orient Express, by Agatha Christie, is a classic detective novel about a murder committed in the middle of the night on a train traveling from the Far East to Europe. While the train is stuck in a snowdrift, the identities, motives and alibis of the passengers are investigated by the renowned Belgian detective Hercule Poirot. As Poirot delves into the mystery of what happened that fateful night, the book explores themes relating to the nature of justice, the power of family ties, and the inevitable triumph of logic.",R.drawable.murder_on_orient_express));
        listBook.add(new MainActivity("Debi","Category: Thriller/Mystery","Description: ঢাকা বিশ্ববিদ্যালয়ের প্রফেসর মিসির আলী যুক্তির সাহায্যে প্রতিনিয়ত কাটাকুটি করেন ভ্রান্ত ধারণা, অযাচিত জমে থাকা ভয়। সবসময় প্রচারবিমুখ এই মানুষের কাছে আসে নতুন এক রোগী। রানু। রানু এবং আনিস। সদ্য বিবাহিত। কাহিনীর শুরুতেই দেখা যায় এক ভয়াবহ রাতে হঠাৎ ভয় পায় রানু। এমন ব্যাপার প্রথম না। বেশ কবার। আনিস যোগাযোগ করে প্রফেসর মিসির আলীর সাথে। মিসির আলী টের পান রানুর উচ্চ মাত্রার ইএসপি ক্ষমতা। কাহিনীসূত্রে এগিয়ে আসে রানুর বাড়িওয়ালা এবং তার দুই মেয়ে। বড় মেয়ে নীলু। বেশ শান্ত মেয়েটি পত্রিকায় বিজ্ঞাপন দেয়া এক অজ্ঞাত যুবকের সাথে জড়িয়ে পড়ে বন্ধুত্বে। সেই বন্ধুত্ব নীলুকে নিয়ে যায় একাকী এক ঘরে। ইতিমধ্যে মিসির আলী রানুর ব্যাপারে জানতে গিয়ে আবিষ্কার করেন রানুর কৈশোরকালের দূর্ঘটনাকে। বেরিয়ে আসে একটি ভাঙা মন্দির আর একজন দেবীর সংশ্লিষ্টতা। নীলুর সাথে কি ঘটেছিল সে রাতে। কেন রানু একই রাতেই পড়ে যায় ভয়াবহ অসুস্থতায়।",R.drawable.debi));
        listBook.add(new MainActivity("Looking For Alaska","Category: Young Adult Fiction","Description: Before. Miles “Pudge” Halter is done with his safe life at home. His whole life has been one big non-event, and his obsession with famous last words has only made him crave “the Great Perhaps” even more (Francois Rabelais, poet). He heads off to the sometimes crazy and anything-but-boring world of Culver Creek Boarding School, and his life becomes the opposite of safe. Because down the hall is Alaska Young. The gorgeous, clever, funny, sexy, self-destructive, screwed up, and utterly fascinating Alaska Young. She is an event unto herself. She pulls Pudge into her world, launches him into the Great Perhaps, and steals his heart. Then. . . . \n" +
                "After. Nothing is ever the same.",R.drawable.looking_for_alaska));
        listBook.add(new MainActivity("All The Light We Cannot See","Category: Historical Fiction","Description: Marie-Laure lives in Paris near the Museum of Natural History, where her father works. When she is twelve, the Nazis occupy Paris and father and daughter flee to the walled citadel of Saint-Malo, where Marie-Laure’s reclusive great uncle lives in a tall house by the sea. With them they carry what might be the museum’s most valuable and dangerous jewel." +
                "\n" +
                "In a mining town in Germany, Werner Pfennig, an orphan, grows up with his younger sister, enchanted by a crude radio they find that brings them news and stories from places they have never seen or imagined. Werner becomes an expert at building and fixing these crucial new instruments and is enlisted to use his talent to track down the resistance. Deftly interweaving the lives of Marie-Laure and Werner, Doerr illuminates the ways, against all odds, people try to be good to one another.",R.drawable.all_the_light_we_cannot_see));
        listBook.add(new MainActivity("Pride and Prejudice","Category: Romance Novel, Satire, Fiction","Description: When Elizabeth Bennet first meets eligible bachelor Fitzwilliam Darcy, she thinks him arrogant and conceited; he is indifferent to her good looks and lively mind. When she later discovers that Darcy has involved himself in the troubled relationship between his friend Bingley and her beloved sister Jane, she is determined to dislike him more than ever. In the sparkling comedy of manners that follows, Jane Austen shows the folly of judging by first impressions and superbly evokes the friendships, gossip and snobberies of provincial middle-class life.",R.drawable.pride_and_prejudice));
        listBook.add(new MainActivity("Fahrenheit 451","Category: Science Fiction / Dystopian Fiction","Description: Guy Montag is a fireman. In his world, where television rules and literature is on the brink of extinction, firemen start fires rather than put them out. His job is to destroy the most illegal of commodities, the printed book, along with the houses in which they are hidden. Montag never questions the destruction and ruin his actions produce, returning each day to his bland life and wife, Mildred, who spends all day with her television \"family.\" But then he meets an eccentric young neighbor, Clarisse, who introduces him to a past where people didn't live in fear and to a present where one sees the world through the ideas in books instead of the mindless chatter of television. When Mildred attempts suicide and Clarisse suddenly disappears, Montag begins to question everything he has ever known. He starts hiding books in his home, and when his pilfering is discovered, the fireman has to run for his life.",R.drawable.fahrenheit));
        listBook.add(new MainActivity("A Brief History of Time","Category: Cosmology","Description: Was there a beginning of time? Could time run backwards? Is the universe infinite or does it have boundaries?\n" +
                "\n" +
                "These are just some of the questions considered in the internationally acclaimed masterpiece by the world renowned physicist - generally considered to have been one of the world's greatest thinkers. It begins by reviewing the great theories of the cosmos from Newton to Einstein, before delving into the secrets which still lie at the heart of space and time, from the Big Bang to black holes, via spiral galaxies and strong theory. To this day A Brief History of Time remains a staple of the scientific canon, and its succinct and clear language continues to introduce millions to the universe and its wonders.",R.drawable.a_brief_history_of_time));
        listBook.add(new MainActivity("The Diary of a Young Girl","Category: Autobiography","Description: First published over sixty years ago, Anne Frank's Diary of a Young Girl has reached millions of young people throughout the world.",R.drawable.the_diary_of_a_young_girl));
        listBook.add(new MainActivity("The Color Purple","Category: Domestic Fiction","Description: The classic, Pulitzer Prize-winning novel that made Alice Walker a household name. \n" +
                "\n" +
                "Set in the deep American South between the wars, The Color Purple is the classic tale of Celie, a young black girl born into poverty and segregation. Raped repeatedly by the man she calls 'father', she has two children taken away from her, is separated from her beloved sister Nettie and is trapped into an ugly marriage. But then she meets the glamorous Shug Avery, singer and magic-maker - a woman who has taken charge of her own destiny. Gradually Celie discovers the power and joy of her own spirit, freeing her from her past and reuniting her with those she loves. ",R.drawable.the_colour_purple));
        listBook.add(new MainActivity("The Power of Now","Category: Self-help Book","Description: Eckhart Tolle demonstrates how to live a healthier and happier life by living in the present moment. \nTo make the journey into The Power of Now we will need to leave our analytical mind and its false created self, the ego, behind. Although the journey is challenging, Eckhart Tolle offers simple language and a question and answer format to show us how to silence our thoughts and create a liberated life.\n" +
                "\n" +
                "Surrender to the present moment, where problems do not exist. It is here we find our joy, are able to embrace our true selves and discover that we are already complete and perfect. If we are able to be fully present and take each step in the Now we will be opening ourselves to the transforming experience of The Power of Now. \n" +
                "\n" +
                "It is in your hands. Discover The Power of Now.",R.drawable.the_power_of_now));
        listBook.add(new MainActivity("The Hitchhiker's Guide to the Galaxy","Category: Comic Science Fiction","Description: The Hitchhiker's Guide to the Galaxy (Nominated as one of America's best-loved novels by PBS's The Great American Read)\n" +
                "Seconds before the Earth is demolished for a galactic freeway, Arthur Dent is saved by Ford Prefect, a researcher for the revised Guide. Together they stick out their thumbs to the stars and begin a wild journey through time and space. The Restaurant at the End of the Universe\n" +
                "The moment before annihilation at the hands of warmongers is a curious time to crave tea. It could only happen to the cosmically displaced Arthur Dent and his comrades as they hurtle across the galaxy in a desperate search for a place to eat. Life, the Universe and Everything\n" +
                "The unhappy inhabitants of planet Krikkit are sick of looking at the night sky- so they plan to destroy it. The universe, that is. Now only five individuals can avert Armageddon: mild-mannered Arthur Dent and his stalwart crew. So Long, and Thanks for All the Fish\n" +
                "Back on Earth, Arthur Dent is ready to believe that the past eight years were all just a figment of his stressed-out imagination. But a gift-wrapped fishbowl with a cryptic inscription thrusts him back to reality. So to speak. Mostly Harmless\n" +
                "Just when Arthur Dent makes the terrible mistake of starting to enjoy life, all hell breaks loose. Can he save the Earth from total obliteration? Can he save the Guide from a hostile alien takeover? Can he save his daughter from herself?",R.drawable.the_hitchhikers_guide));
        listBook.add(new MainActivity("The Book Thief","Category: Young Adult Fiction / Historical Fiction","Description: When Death has a story to tell, you listen. It is 1939. Nazi Germany. The country is holding its breath. Death has never been busier, and will become busier still. Liesel Meminger is a foster girl living outside of Munich, who scratches out a meager existence for herself by stealing when she encounters something she can't resist-books. With the help of her accordion-playing foster father, she learns to read and shares her stolen books with her neighbors during bombing raids as well as with the Jewish man hidden in her basement.",R.drawable.the_book_thief));
        listBook.add(new MainActivity("To Kill A Mockingbird","Category:Book","Description:Book",R.drawable.to_kill_a_mockingbird));
        listBook.add(new MainActivity("The Devil Wears Prada","Category:Book","Description:Book",R.drawable.the_devil_wears_prada));
        listBook.add(new MainActivity("All the Bright Places","Category:Book","Description:Book",R.drawable.all_the_bright_places));
        listBook.add(new MainActivity("The Old Man and The Sea","Category:Book","Description:Book",R.drawable.old_man_and_the_sea));
        listBook.add(new MainActivity("The Secret life of Bees","Category:Book","Description:Book",R.drawable.the_secret_life_of_bees));
        listBook.add(new MainActivity("Robi Nishi","Category:Book","Description:Book",R.drawable.robo_nishi));
        listBook.add(new MainActivity("Da Vinci Code","Category:Book","Description:Book",R.drawable.davinci_code));
        listBook.add(new MainActivity("The Adventures of Sherlock Holmes","Category:Book","Description:Book",R.drawable.the_adventures_of_sherlock_holmes));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listBook);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(myAdapter);

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

           // FragmentManager fragmentManager = getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.content_id, new Favourite_Fragment()).commit();
            //Toast.makeText(this, "Favourite Readings",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

           // Favourite_Fragment favourites_fragment = new Favourite_Fragment();
            //FragmentManager fragmentManager = getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.content_id, favourites_fragment).commit();
            Toast.makeText(this, "Favourite Readings",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
