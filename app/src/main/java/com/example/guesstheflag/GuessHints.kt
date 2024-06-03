package com.example.guesstheflag

import android.content.Context
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme
import com.example.guesstheflag.ui.theme.Poppins
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

class GuessHints : ComponentActivity() {

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
        "AX" to "\u00c5land Islands",
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
        "CN" to "China (People's Republic of China)",
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
        "SX" to "Sint Maarten (Dutch part)",
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
                    Text(text = "Guess Hints")
                    val switchState = intent.getBooleanExtra("SWITCH_STATE", false)
                    GuessHintsContent(flagDetails = flagDetails, context = applicationContext,switchState)
                }
            }
        }
    }
}

@Composable
fun GuessHintsContent(flagDetails : Map<String,String>, context: Context, switchState : Boolean){

    //Getting a random id(flag) from the map
    var randomFlagCode by rememberSaveable { mutableStateOf(flagDetails.keys.random())}  // https://developer.android.com/develop/ui/compose/state-saving

    //Getting the file name from the drawable resources
    var randomImageFileName by rememberSaveable {
        mutableStateOf( getResourceId(randomFlagCode, context))
    }

    // Get the country name corresponding to the random flag code
    var randomCountryName = flagDetails[randomFlagCode] ?: ""

    //To hold the length of the flag name of the randomly displayed flag
    var letters by rememberSaveable { mutableStateOf(List(randomCountryName.length){ mutableStateOf(AnnotatedString("_")) }) }

    //To store the users guess
    var guess by rememberSaveable { mutableStateOf("") }

    //To hold the incorrect attempt of the user
    var incorrectAttemptCount by rememberSaveable { mutableStateOf(0) }

    //to hold the correct guess made by the user
    var isCorrectGuess by rememberSaveable { mutableStateOf(true)}

    //to hold the button text and convert it accordingly
    var buttonText by rememberSaveable { mutableStateOf("Submit") }

    //variable to handle the seconds from the timer
    var countDown by rememberSaveable { mutableStateOf(10) }

    //variable to generate the results if all blanks are guessed
    var allCorrect by rememberSaveable { mutableStateOf(false) }

    //to generate the text if incorrect guesses are made
    var incorrect by rememberSaveable {
        mutableStateOf(false)
    }


    // Function to generate a new random flag code
    fun generateNewFlag(){
        randomFlagCode = flagDetails.keys.random()
        randomImageFileName = getResourceId(randomFlagCode, context)
        randomCountryName = flagDetails[randomFlagCode] ?: ""

        // Reset other necessary state variables here
        buttonText = "Submit"
        countDown = 10
        guess = ""
        letters = List(randomCountryName.length) { mutableStateOf(AnnotatedString("_")) }
        incorrect = false
        allCorrect = false
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFB8C8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //if the passed switch state is true executing the timer

        if(switchState){
            LaunchedEffect(Unit){
                while(countDown > 0){
                    delay(1.seconds)
                    countDown--
                }

                //displaying the answers and changing the button value to next when the countdown is over
                if(countDown == 0){
                    buttonText = "Next"
                    incorrect = true

                }
            }
        }

        //To display the countdown
        Text(
            text =  "Timer :"+ countDown.toString(),
            fontSize = 15.sp,
            fontFamily = Poppins,
            color = Color(0xFF6650a4),
            textAlign = TextAlign.Left
        )

        //To display the flag
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(170.dp)
                .border(
                    width = 3.dp,
                    color = Color(0xFF6650a4),
                    shape = RoundedCornerShape(5.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = randomImageFileName),
                contentDescription = "flag",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
            )

        }


        Log.d("Displayed Country Name ","$randomCountryName")

        //To display the blanks
        LazyRow {
            items(letters.size) { index ->
                Text(
                    text = letters[index].value,
                    color = Color.Magenta,
                    fontFamily =  Poppins,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(7.dp))
            }
        }

        //Textfield for the user to enter the guess
        TextField(
            value = guess,
            onValueChange = {newValue -> guess = newValue},
            label = { Text(
                text = "Enter Your Guess ",
                fontSize = 10.sp
            )},
            modifier = Modifier
                .background(Color.Transparent),

        )

        Button(
            onClick =
            {
                //Once the submit button is clicked, checking if user has made a correct or wrong guess using the updateCharacter function
                if (buttonText == "Submit"){
                    isCorrectGuess = updateCharacters(guess, randomCountryName, letters)

                    //if the user has made a correct guess
                    if (isCorrectGuess) {
                        // Check if all letters are guessed correctly
                        val allLettersGuessed = letters.all { it.value.text != "_" }
                        if (allLettersGuessed) {
                            // If all letters are guessed correctly, change button text to "Next"
                            buttonText = "Next"
                            allCorrect = true
                        }

                    } else { //incrementing the incorrectAttemptCount if user has made a correct guess
                        incorrectAttemptCount++
                        incorrect = true

                        //Changing the button text to next if the user has made more than 3 or more incorrect guesses
                        if(incorrectAttemptCount>=3 || countDown == 0){
                            buttonText = "Next"
                        }
                    }
                }else{
                    //Generating a new random flag when the user clicks next
                    generateNewFlag()
                }
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .width(170.dp)
                .height(40.dp)
                .padding(5.dp)
            ,
            colors  = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6650a4)
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = buttonText,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            )
        }

        //if the user has guessed the flag name correctly displaying the correct text
        if (allCorrect) {
            Text(
                text = "CORRECT",
                color = Color.Green,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        //if the user didnt guess the flagname correctly  displaying wrong text
        if(incorrect){
            if (incorrectAttemptCount >= 3 || countDown == 0) {
                Text(
                    text = "WRONG!",
                    color = Color.Red,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = "Correct Answer : $randomCountryName",
                    color = Color.Blue,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

/* function to update the letters if they are correct*/
fun updateCharacters(letter: String, randomCountryName:String, letters: List<MutableState<AnnotatedString>>):Boolean{
    var allGuessed = false
    randomCountryName.forEachIndexed { index, char ->
        //if the users guess is equal to any index from the displayed country name append that value to that index
        if (char.toString().equals(letter, ignoreCase = true)) {
            letters[index].value = AnnotatedString(letter)
            allGuessed = true
            Log.d("char from the name" , "$char")
            Log.d("guess of the user","the $letter")

        }
    }

    return allGuessed
}







