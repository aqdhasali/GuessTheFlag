package com.example.guesstheflag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme
import com.example.guesstheflag.ui.theme.Poppins
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AdvancedLevel : ComponentActivity() {

    //Declaring the flag code and flag names
    val flagDetails = mapOf(
        "AD" to "Andorra",
        "AE" to "United Arab Emirates",
        "AF" to "Afghanistan",
        "AG" to "Antigua and Barbuda",
        "AI" to "Anguilla",
        "AL" to "Albania",
        "AM" to "Armenia",
        "AO" to "Angola",
        "AQ" to "Antarctica",
        "AR" to "Argentina",
        "AS" to "American Samoa",
        "AT" to "Austria",
        "AU" to "Australia",
        "AW" to "Aruba",
        "AX" to "Aland Islands",
        "AZ" to "Azerbaijan",
        "BA" to "Bosnia and Herzegovina",
        "BB" to "Barbados",
        "BD" to "Bangladesh",
        "BE" to "Belgium",
        "BF" to "Burkina Faso",
        "BG" to "Bulgaria",
        "BH" to "Bahrain",
        "BI" to "Burundi",
        "BJ" to "Benin",
        "BL" to "Saint Barthélemy",
        "BM" to "Bermuda",
        "BN" to "Brunei Darussalam",
        "BO" to "Bolivia, Plurinational State of",
        "BQ" to "Caribbean Netherlands",
        "BR" to "Brazil",
        "BS" to "Bahamas",
        "BT" to "Bhutan",
        "BV" to "Bouvet Island",
        "BW" to "Botswana",
        "BY" to "Belarus",
        "BZ" to "Belize",
        "CA" to "Canada",
        "CC" to "Cocos (Keeling) Islands",
        "CD" to "Congo, the Democratic Republic of the",
        "CF" to "Central African Republic",
        "CG" to "Republic of the Congo",
        "CH" to "Switzerland",
        "CI" to "Côte d'Ivoire",
        "CK" to "Cook Islands",
        "CL" to "Chile",
        "CM" to "Cameroon",
        "CN" to "China",
        "CO" to "Colombia",
        "CR" to "Costa Rica",
        "CU" to "Cuba",
        "CV" to "Cape Verde",
        "CW" to "Curaçao",
        "CX" to "Christmas Island",
        "CY" to "Cyprus",
        "CZ" to "Czech Republic",
        "DE" to "Germany",
        "DJ" to "Djibouti",
        "DK" to "Denmark",
        "DM" to "Dominica",
        "DOM" to "Dominican Republic",
        "DZ" to "Algeria",
        "EC" to "Ecuador",
        "EE" to "Estonia",
        "EG" to "Egypt",
        "EH" to "Western Sahara",
        "ER" to "Eritrea",
        "ES" to "Spain",
        "ET" to "Ethiopia",
        "EU" to "Europe",
        "FI" to "Finland",
        "FJ" to "Fiji",
        "FK" to "Falkland Islands (Malvinas)",
        "FM" to "Micronesia, Federated States of",
        "FO" to "Faroe Islands",
        "FR" to "France",
        "GA" to "Gabon",
        "GBENG" to "England",
        "GBNIR" to "Northern Ireland",
        "GBSCT" to "Scotland",
        "GBWLS" to "Wales",
        "GB" to "United Kingdom",
        "GD" to "Grenada",
        "GE" to "Georgia",
        "GF" to "French Guiana",
        "GG" to "Guernsey",
        "GH" to "Ghana",
        "GI" to "Gibraltar",
        "GL" to "Greenland",
        "GM" to "Gambia",
        "GN" to "Guinea",
        "GP" to "Guadeloupe",
        "GQ" to "Equatorial Guinea",
        "GR" to "Greece",
        "GS" to "South Georgia and the South Sandwich Islands",
        "GT" to "Guatemala",
        "GU" to "Guam",
        "GW" to "Guinea-Bissau",
        "GY" to "Guyana",
        "HK" to "Hong Kong",
        "HM" to "Heard Island and McDonald Islands",
        "HN" to "Honduras",
        "HR" to "Croatia",
        "HT" to "Haiti",
        "HU" to "Hungary",
        "ID" to "Indonesia",
        "IE" to "Ireland",
        "IL" to "Israel",
        "IM" to "Isle of Man",
        "IN" to "India",
        "IO" to "British Indian Ocean Territory",
        "IQ" to "Iraq",
        "IR" to "Iran, Islamic Republic of",
        "IS" to "Iceland",
        "IT" to "Italy",
        "JE" to "Jersey",
        "JM" to "Jamaica",
        "JO" to "Jordan",
        "JP" to "Japan",
        "KE" to "Kenya",
        "KG" to "Kyrgyzstan",
        "KH" to "Cambodia",
        "KI" to "Kiribati",
        "KM" to "Comoros",
        "KN" to "Saint Kitts and Nevis",
        "KP" to "Korea, Democratic People's Republic of",
        "KR" to "Korea, Republic of",
        "KW" to "Kuwait",
        "KY" to "Cayman Islands",
        "KZ" to "Kazakhstan",
        "LA" to "Laos (Lao People's Democratic Republic)",
        "LB" to "Lebanon",
        "LC" to "Saint Lucia",
        "LI" to "Liechtenstein",
        "LK" to "Sri Lanka",
        "LR" to "Liberia",
        "LS" to "Lesotho",
        "LT" to "Lithuania",
        "LU" to "Luxembourg",
        "LV" to "Latvia",
        "LY" to "Libya",
        "MA" to "Morocco",
        "MC" to "Monaco",
        "MD" to "Moldova, Republic of",
        "ME" to "Montenegro",
        "MF" to "Saint Martin",
        "MG" to "Madagascar",
        "MH" to "Marshall Islands",
        "MK" to "North Macedonia",
        "ML" to "Mali",
        "MM" to "Myanmar",
        "MN" to "Mongolia",
        "MO" to "Macao",
        "MP" to "Northern Mariana Islands",
        "MQ" to "Martinique",
        "MR" to "Mauritania",
        "MS" to "Montserrat",
        "MT" to "Malta",
        "MU" to "Mauritius",
        "MV" to "Maldives",
        "MW" to "Malawi",
        "MX" to "Mexico",
        "MY" to "Malaysia",
        "MZ" to "Mozambique",
        "NA" to "Namibia",
        "NC" to "New Caledonia",
        "NE" to "Niger",
        "NF" to "Norfolk Island",
        "NG" to "Nigeria",
        "NI" to "Nicaragua",
        "NL" to "Netherlands",
        "NO" to "Norway",
        "NP" to "Nepal",
        "NR" to "Nauru",
        "NU" to "Niue",
        "NZ" to "New Zealand",
        "OM" to "Oman",
        "PA" to "Panama",
        "PE" to "Peru",
        "PF" to "French Polynesia",
        "PG" to "Papua New Guinea",
        "PH" to "Philippines",
        "PK" to "Pakistan",
        "PL" to "Poland",
        "PM" to "Saint Pierre and Miquelon",
        "PN" to "Pitcairn",
        "PR" to "Puerto Rico",
        "PS" to "Palestine",
        "PT" to "Portugal",
        "PW" to "Palau",
        "PY" to "Paraguay",
        "QA" to "Qatar",
        "RE" to "Réunion",
        "RO" to "Romania",
        "RS" to "Serbia",
        "RU" to "Russian Federation",
        "RW" to "Rwanda",
        "SA" to "Saudi Arabia",
        "SB" to "Solomon Islands",
        "SC" to "Seychelles",
        "SD" to "Sudan",
        "SE" to "Sweden",
        "SG" to "Singapore",
        "SH" to "Saint Helena, Ascension and Tristan da Cunha",
        "SI" to "Slovenia",
        "SJ" to "Svalbard and Jan Mayen Islands",
        "SK" to "Slovakia",
        "SL" to "Sierra Leone",
        "SM" to "San Marino",
        "SN" to "Senegal",
        "SO" to "Somalia",
        "SR" to "Suriname",
        "SS" to "South Sudan",
        "ST" to "Sao Tome and Principe",
        "SV" to "El Salvador",
        "SX" to "Sint Maarten",
        "SY" to "Syrian Arab Republic",
        "SZ" to "Swaziland",
        "TC" to "Turks and Caicos Islands",
        "TD" to "Chad",
        "TF" to "French Southern Territories",
        "TG" to "Togo",
        "TH" to "Thailand",
        "TJ" to "Tajikistan",
        "TK" to "Tokelau",
        "TL" to "Timor-Leste",
        "TM" to "Turkmenistan",
        "TN" to "Tunisia",
        "TO" to "Tonga",
        "TR" to "Turkey",
        "TT" to "Trinidad and Tobago",
        "TV" to "Tuvalu",
        "TW" to "Taiwan (Republic of China)",
        "TZ" to "Tanzania, United Republic of",
        "UA" to "Ukraine",
        "UG" to "Uganda",
        "UM" to "US Minor Outlying Islands",
        "US" to "United States",
        "UY" to "Uruguay",
        "UZ" to "Uzbekistan",
        "VA" to "Holy See (Vatican City State)",
        "VC" to "Saint Vincent and the Grenadines",
        "VE" to "Venezuela, Bolivarian Republic of",
        "VG" to "Virgin Islands, British",
        "VI" to "Virgin Islands, U.S.",
        "VN" to "Vietnam",
        "VU" to "Vanuatu",
        "WF" to "Wallis and Futuna Islands",
        "WS" to "Samoa",
        "XK" to "Kosovo",
        "YE" to "Yemen",
        "YT" to "Mayotte",
        "ZA" to "South Africa",
        "ZM" to "Zambia",
        "ZW" to "Zimbabwe"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheFlagTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val switchState = intent.getBooleanExtra("SWITCH_STATE", false)
                    AdvancedLevelContent(flagDetails = flagDetails, switchState)
                }
            }
        }
    }
}


@Composable

fun AdvancedLevelContent(flagDetails : Map<String,String>, switchState:Boolean){
    //declaring the context
    val context = LocalContext.current

    //Getting 3 random image files with their details and storing into 3 different variables

    //Flag one details
    //Getting the flag code from the map
    var randomFlagCodeOne  by rememberSaveable { mutableStateOf( flagDetails.keys.random() )}  // https://developer.android.com/develop/ui/compose/state-saving
    //Getting the corresponding image
    var randomImageFileNameOne by rememberSaveable {
        mutableStateOf( getResourceId(randomFlagCodeOne, context) )
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameOne = flagDetails[randomFlagCodeOne] ?: ""


    //Flag two details
    //Getting the flag code from the map
    var randomFlagCodeTwo by rememberSaveable { mutableStateOf(flagDetails.keys.random())}
    //Getting the corresponding image
    var randomImageFileNameTwo by rememberSaveable {
        mutableStateOf(getResourceId(randomFlagCodeTwo, context))
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameTwo = flagDetails[randomFlagCodeTwo] ?: ""

    //Flag three details
    //Getting the flag code from the map
    var randomFlagCodeThree  by rememberSaveable { mutableStateOf( flagDetails.keys.random() ) }
    //Getting the corresponding image
    var randomImageFileNameThree by rememberSaveable {
        mutableStateOf( getResourceId(randomFlagCodeThree, context) )
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameThree = flagDetails[randomFlagCodeThree] ?: ""


    //Declaring variables to store user's guesses
    var guessOne by rememberSaveable { mutableStateOf("") }
    var guessTwo by rememberSaveable { mutableStateOf("") }
    var guessThree by rememberSaveable { mutableStateOf("") }

    //Declaring variables to set the color accordingly
    var incorrectGuess = false

    //variable to check if each of the guesses that are made is correct or not
    var isGuessOneCorrect by rememberSaveable { mutableStateOf(false) }
    var isGuessTwoCorrect by rememberSaveable { mutableStateOf(false) }
    var isGuessThreeCorrect by rememberSaveable { mutableStateOf(false) }

    //variable to hold if any guesses are true out of the 3
    var isAnyGuessCorrect by rememberSaveable { mutableStateOf(false) }
    //variable to hold if all 3 guesses are correct
    val allGuessesCorrect = isGuessOneCorrect && isGuessTwoCorrect && isGuessThreeCorrect

    //to make the text fields disabled after any incorrect attempt
    val guessOneEnabled = !isGuessOneCorrect
    val guessTwoEnabled = !isGuessTwoCorrect
    val guessThreeEnabled = !isGuessThreeCorrect

    //Variable to hold the submit count
    var submitCount by rememberSaveable { mutableStateOf(0) }

    //Variable to show the correct answers
    var showCorrectAnswers by rememberSaveable { mutableStateOf(false) }

    //Variable to hold the score
    var score by rememberSaveable { mutableStateOf(0) }

    //Variables to check if the score is already calculated or not
    var isGuessOneEvaluated by rememberSaveable { mutableStateOf(false) }
    var isGuessTwoEvaluated by rememberSaveable{ mutableStateOf(false) }
    var isGuessThreeEvaluated by rememberSaveable { mutableStateOf(false) }


    //Variable to hold the submit buttons count limit to freeze the text field
    var submitCountLimit by rememberSaveable { mutableStateOf(false) }

    var textFieldsEnabled by rememberSaveable { mutableStateOf(true)}


    // Variable to track whether the score box is displayed
    var scoreBoxDisplayed by rememberSaveable { mutableStateOf(false)}

    //variable to handle the ticks
    var countDown by rememberSaveable { mutableStateOf(10) }

    // Function to generate a new random flag code
    fun generateNewFlag() {
        randomFlagCodeOne = flagDetails.keys.random()
        randomImageFileNameOne = getResourceId(randomFlagCodeOne, context)
        randomCountryNameOne = flagDetails[randomFlagCodeOne] ?: ""

        randomFlagCodeTwo = flagDetails.keys.random()
        randomImageFileNameTwo = getResourceId(randomFlagCodeTwo, context)
        randomCountryNameTwo = flagDetails[randomFlagCodeTwo] ?: ""

        randomFlagCodeThree = flagDetails.keys.random()
        randomImageFileNameThree = getResourceId(randomFlagCodeThree, context)
        randomCountryNameThree = flagDetails[randomFlagCodeThree] ?: ""

        // Resetting the guess variables to empty strings
        guessOne = ""
        guessTwo = ""
        guessThree = ""

        // Resetting the correctness variables to false
        isGuessOneCorrect = false
        isGuessTwoCorrect = false
        isGuessThreeCorrect = false

        // Resetting the evaluation variables to false
        isGuessOneEvaluated = false
        isGuessTwoEvaluated = false
        isGuessThreeEvaluated = false

        // Set the text fields enabled
        textFieldsEnabled = true

        // Hiding the answers and the score box
        showCorrectAnswers = false
        scoreBoxDisplayed = false

        //Resetting the submit count back to 0
        submitCount = 0

        // Resetting the count down back to 10
        countDown = 10

    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .width(400.dp)
            .background(Color(0xFFEFB8C8))
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        // If the passed switch state is true executing the timer

        if(switchState){
            LaunchedEffect(Unit){
                while(countDown > 0){
                    delay(1.seconds)
                    countDown--
                }

                // Showing all the correct answers after the timer is over
                showCorrectAnswers = true
            }
        }

        // Text to hold the score
        Text(
            text = "Score : $score",
            fontSize = 15.sp,
            fontFamily = Poppins,
            color = Color(0xFF6650a4),
            textAlign = TextAlign.Right
        )

        // Text to hold the timer
        Text(
            text = "Timer : "+countDown.toString() ,
            fontSize = 15.sp,
            fontFamily = Poppins,
            color = Color(0xFF6650a4),
            textAlign = TextAlign.Left
        )

        Log.d("Flag one","$randomCountryNameOne")
        Log.d("Flag Two","$randomCountryNameTwo")
        Log.d("Flag Three","$randomCountryNameThree")


        //To display the 3 flags
        Box{
            Row {
                Box{
                    Image(
                        painter = painterResource(id = randomImageFileNameOne) ,
                        contentDescription = "flag-one",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp),
                    )
                }
                Spacer(modifier = Modifier.width(7.dp))

                Box{
                    Image(
                        painter = painterResource(id = randomImageFileNameTwo),
                        contentDescription = "flag-two",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp),
                    )
                }

                Spacer(modifier = Modifier.width(7.dp))

                Box{
                    Image(
                        painter = painterResource(id = randomImageFileNameThree),
                        contentDescription = "flag-three",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp),
                    )
                }

            }
        }
        

        //to display the 3 textfields
        TextField(
            value = guessOne,
            onValueChange = { newGuess ->
                guessOne = newGuess
            },
            enabled = textFieldsEnabled && guessOneEnabled,
            modifier = Modifier
                //Changing the background color of the textfield if the guesses are correct or wrong
                .background(if (isGuessOneCorrect) Color.Green else if (incorrectGuess && !isAnyGuessCorrect) Color.Red else Color.Transparent),
            label = {Text(text = "Enter First Flag  ") }
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = guessTwo,
            onValueChange = { newGuess ->
                guessTwo = newGuess
            },
            enabled = textFieldsEnabled && guessTwoEnabled,
            modifier = Modifier
                //Changing the background color of the textfield if the guesses are correct or wrong
                .background(if (isGuessTwoCorrect) Color.Green else if (incorrectGuess && !isAnyGuessCorrect) Color.Red else Color.Transparent),
            label = { Text(text = "Enter Second Flag")}
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = guessThree,
            onValueChange = { newGuess ->
                guessThree = newGuess},
            enabled = textFieldsEnabled && guessThreeEnabled,
            modifier = Modifier
                //Changing the background color of the textfield if the guesses are correct or wrong
                .background(if (isGuessThreeCorrect) Color.Green else if (incorrectGuess && !isAnyGuessCorrect) Color.Red else Color.Transparent)
                ,

            label = { Text(text = "Enter Third Flag")}
        )


        Button(
            onClick = {
                //passing the guesses and checking whether the guesses made are correct or incorrect
                val (newCorrectGuessOne, newIncorrectGuessOne) = handleAnswer(guessOne.toLowerCase(), randomCountryNameOne.toLowerCase(), context)
                val (newCorrectGuessTwo, newIncorrectGuessTwo) = handleAnswer(guessTwo.toLowerCase(), randomCountryNameTwo.toLowerCase(), context)
                val (newCorrectGuessThree, newIncorrectGuessThree) = handleAnswer(guessThree.toLowerCase(), randomCountryNameThree.toLowerCase(), context)


                //Increasing the submit count
                submitCount +=1

               //checking if atleast one of the guesses is true
                isAnyGuessCorrect = newCorrectGuessOne || newCorrectGuessTwo || newCorrectGuessThree

                // Update the state variables based on the correctness of each guess
                isGuessOneCorrect = newCorrectGuessOne
                isGuessTwoCorrect = newCorrectGuessTwo
                isGuessThreeCorrect = newCorrectGuessThree


                // Update the score based on the correctness of guesses
                if (isGuessOneCorrect && !isGuessOneEvaluated){
                    score++
                    isGuessOneEvaluated = true
                }

                if(isGuessTwoCorrect && !isGuessTwoEvaluated) {
                    score++
                    isGuessTwoEvaluated = true
                }

                if(isGuessThreeCorrect && !isGuessThreeEvaluated)
                {
                    score++
                    isGuessThreeEvaluated = true
                }

                if (submitCount >= 3) {
                    submitCountLimit = true
                }

                // check if submit count reaches 3 and there is at least one incorrect guess
                if (submitCount == 3 && !allGuessesCorrect && (isGuessOneCorrect || isGuessTwoCorrect || isGuessThreeCorrect)) {
                    showCorrectAnswers = true
                }

                // Update the enabled state of text fields based on submission limit
                textFieldsEnabled = !submitCountLimit


                // If the score box is displayed, add an intent to navigate to the next screen
                if (scoreBoxDisplayed) {
                    // Passing the current score for the next level as well
                    generateNewFlag()
                }

            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .width(170.dp)
                .height(50.dp)
                .padding(5.dp)
            ,
            colors  = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6650a4)
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = if (scoreBoxDisplayed) "Next" else "Submit",
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            )
        }

        // Display the correct answers box based on the showCorrectAnswers state variable
        if (showCorrectAnswers || submitCount==3 || countDown==0) {
            scoreBoxDisplayed = true
            Box(
                modifier = Modifier
                    .background(Color(0xFFEFB8C8))
                    .border(
                        width = 3.dp,
                        color = Color(0xFF6650a4),
                        shape = RoundedCornerShape(5.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color(0xFFEFB8C8))
                        .padding(16.dp)

                ) {
                    Text(
                        text = "Correct Answers",
                        fontSize = 15.sp,
                        fontFamily = Poppins,
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // displaying the correct answers of the incorrect guesses
                    if (!isGuessOneCorrect) {
                        Text(
                            text = "Flag One: $randomCountryNameOne",
                            color = Color.Blue,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center
                        )
                    }

                    if (!isGuessTwoCorrect) {
                        Text(
                            text = "Flag Two: $randomCountryNameTwo",
                            color = Color.Blue,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center
                        )
                    }

                    if (!isGuessThreeCorrect) {
                        Text(
                            text = "Flag Three: $randomCountryNameThree",
                            color = Color.Blue,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

/*This function checks if the user's guess matches with relevant country name, if so it returns a pair of boolean values */
fun handleAnswer(guess: String, randomCountryName: String, context: Context): Pair<Boolean, Boolean> {
    return if (guess == randomCountryName) {
        true to false // Return true for correct guess and false for incorrect guess
    } else {
        false to true // Return false for correct guess and true for incorrect guess
    }
}




