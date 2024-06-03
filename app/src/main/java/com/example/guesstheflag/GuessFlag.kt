package com.example.guesstheflag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme
import com.example.guesstheflag.ui.theme.Poppins
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class GuessFlag : ComponentActivity() {

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
                val switchState = intent.getBooleanExtra("SWITCH_STATE", false)
               GuessFlagContent(flagDetails, context = applicationContext,switchState)

            }
        }
    }
}

@Composable
fun GuessFlagContent(flagDetails : Map<String,String>, context: Context, switchState :Boolean){
    //Getting 3 random image files with their details and storing into 3 different variables

    //Flag one details
    //Getting the flag code from the map
    var randomFlagCodeOne  by rememberSaveable { mutableStateOf(flagDetails.keys.random())}  // https://developer.android.com/develop/ui/compose/state-saving
    //Getting the corresponding image
    var randomImageFileNameOne by rememberSaveable {
        mutableStateOf(getResourceId(randomFlagCodeOne, context))
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameOne = flagDetails[randomFlagCodeOne] ?: ""


    //Flag two details
    //Getting the flag code from the map
    var randomFlagCodeTwo  by rememberSaveable { mutableStateOf( flagDetails.keys.random() )}
    //Getting the corresponding image
    var randomImageFileNameTwo by rememberSaveable {
        mutableStateOf( getResourceId(randomFlagCodeTwo, context) )
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameTwo = flagDetails[randomFlagCodeTwo] ?: ""

    //Flag three details
    //Getting the flag code from the map
    var randomFlagCodeThree  by rememberSaveable { mutableStateOf( flagDetails.keys.random() )}
    //Getting the corresponding image
    var randomImageFileNameThree by rememberSaveable {
        mutableStateOf(getResourceId(randomFlagCodeThree, context))
    }
    // Get the country name corresponding to the random flag code
    var randomCountryNameThree = flagDetails[randomFlagCodeThree] ?: ""

    //adding the 3 random country name that are generated to a list and getting one out of it randomly
    var countryNames = listOf(randomCountryNameOne,randomCountryNameTwo,randomCountryNameThree)
    var demoCountryName by rememberSaveable {
        mutableStateOf( countryNames.random() )
    }

    //To hold the if a correct or wrong guess is made by the user
    var correctGuess by remember { mutableStateOf(false); }
    var incorrectGuess by remember { mutableStateOf(false); }

    //Initial background for the buttons
    val bgOne = remember { mutableStateOf(Color.Magenta) }
    val bgTwo = remember { mutableStateOf(Color.Magenta) }
    val bgThree = remember { mutableStateOf(Color.Magenta) }

    //Declaring the contect
    val context = LocalContext.current

    //  state variables to track button clicks
    var buttonClicked by rememberSaveable { mutableStateOf(false) }

    //variable to handle the ticks
    var countDown by remember { mutableStateOf(10) }

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

        countryNames = listOf(randomCountryNameOne,randomCountryNameTwo,randomCountryNameThree)
        demoCountryName = countryNames.random();


        correctGuess = false // Reset correctGuess state
        incorrectGuess = false // Reset incorrectGuess state
        buttonClicked = false // Reset buttonClicked state

        // Reset other necessary state variables here
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
    ) {
        //If the passed switch state is true start the counter
        if(switchState){
            LaunchedEffect(Unit){
                while(countDown > 0){
                    delay(1.seconds)
                    countDown--
                }

                // Display the relevant answer after the timer is over
                buttonClicked = true
                if (randomCountryNameOne == demoCountryName || randomCountryNameTwo == demoCountryName || randomCountryNameThree == demoCountryName) {
                    correctGuess = true
                } else {
                    incorrectGuess = true
                }
            }
        }

        //To display the timer
        Text(
            text = "Timer : "+countDown.toString() ,
            fontSize = 15.sp,
            fontFamily = Poppins,
            color = Color(0xFF6650a4),
            textAlign = TextAlign.Left
        )

        //To display the flags
        Row {
            //Displaying flag One
            Button(
                onClick = {

                    //Checking if this button is clicked
                    if (!buttonClicked) {
                        buttonClicked = true
                        if(randomCountryNameOne.equals(demoCountryName)){ //checking if random country name 1 equals the demo country name
                            correctGuess = true; // if it matches displaying the correct guess
                        }
                        else{
                            incorrectGuess = true;  // if it doesn't match displaying the wrong guess
                        }
                    }


                },
                colors = ButtonDefaults.buttonColors(containerColor = bgOne.value),
                enabled = !buttonClicked,
                shape = RoundedCornerShape(5.dp)
            ) {
                Image(
                    painter = painterResource(id = randomImageFileNameOne),
                    contentDescription = "flag-one" ,
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            //Displaying flag two
            Button(
                onClick = {
                    //Checking if this button is clicked
                    if(!buttonClicked){
                        buttonClicked = true
                        if(randomCountryNameTwo.equals(demoCountryName)){ //checking if random country name 2 equals the demo country name
                            correctGuess = true; // if it matches displaying the correct guess

                        }
                        else{
                            incorrectGuess = true; // if it doesn't match displaying the wrong guess

                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = bgTwo.value),
                enabled = !buttonClicked,
                shape = RoundedCornerShape(5.dp)
            ) {
                Image(
                    painter = painterResource(id = randomImageFileNameTwo),
                    contentDescription = "flag-two",
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            //Displaying flag three
            Button(
                onClick = {
                    //Checking if this button is clicked
                    if (!buttonClicked){
                        buttonClicked = true
                        if(randomCountryNameThree.equals(demoCountryName)){ //checking if random country name 3 equals the demo country name
                            correctGuess = true; // if it matches displaying the correct guess
                        }
                        else{
                            incorrectGuess = true; // if it doesnt match displaying the wrong guess
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = bgThree.value),
                enabled = !buttonClicked,
                shape = RoundedCornerShape(5.dp)
            ) {
                Image(
                    painter = painterResource(id = randomImageFileNameThree),
                    contentDescription = "flag-three",
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                )

            }
        }

        Text(
            text = demoCountryName,
            color = Color(0xFF6650a4),
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

    // for the nex button
        Button(
            onClick = {
                generateNewFlag()
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .width(170.dp)
                .height(44.dp)
                .padding(5.dp)
            ,
            colors  = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6650a4)
            ),
            shape = RoundedCornerShape(5.dp)
            )
        {
            Text(
                text = "Next",
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            )
        }


        //if user has clicked the correct flag show the correct result
        if(correctGuess){
            Text(
                text = "CORRECT!",
                color = Color.Green,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            );

            //Show the incorrect result if the user has clicked the wrong flag
        } else if(incorrectGuess){
            Text(
                text = "INCORRECT",
                color = Color.Red,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            );
        }
}
}





