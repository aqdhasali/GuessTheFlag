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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme
import com.example.guesstheflag.ui.theme.Poppins
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.time.Duration.Companion.seconds


class GuessCountry : ComponentActivity() {

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

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Box(
                        modifier = Modifier.background(Color(0xFFEFB8C8))

                    ){

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
//                           Getting the switch state that was passed from the main activity
                            val switchState = intent.getBooleanExtra("SWITCH_STATE", false)

                            GuessCountryContent(
                                flagDetails = flagDetails,
                                context = applicationContext,switchState)
                            Log.d("Test", "Line before executing the content function")
                        }
                    }
                    }

            }
        }
    }
}


@Composable
fun GuessCountryContent(flagDetails : Map<String,String>, context: Context, switchState :Boolean){
    //Declaring the variable to store the selected country by the user
    var selectedCountry by rememberSaveable { mutableStateOf("") } // https://developer.android.com/develop/ui/compose/state-saving

    //Getting a random id(flag) from the hashMap
    var randomFlagCode  by rememberSaveable {
        mutableStateOf(flagDetails.keys.random())
    }

    //Getting the file name relevant to that code
    var randomImageFileName by rememberSaveable {
        mutableStateOf( getResourceId(randomFlagCode, context))
    }

    // Get the country name corresponding to the random flag code
    val randomCountryName = flagDetails[randomFlagCode] ?: ""

    // State variables to control the answers text visibility
    var correctTextVisible by rememberSaveable { mutableStateOf(false) }
    var wrongTextVisible by rememberSaveable { mutableStateOf(false) }
    var correctAnswerVisible by rememberSaveable { mutableStateOf(false) }

    //State variable to change the button texts to either Submit - Next
    var buttonText by rememberSaveable { mutableStateOf("Submit")}
    //State varibale to display the results once the button is clicked
    var displayResults by rememberSaveable { mutableStateOf(false) }


    //variable to handle the seconds from the timer
    var countDown by rememberSaveable { mutableStateOf(10) }


    // Function to generate a new random flag code
    fun generateNewFlag() {
        countDown = 10
        randomFlagCode = flagDetails.keys.random()
        randomImageFileName = getResourceId(randomFlagCode, context)
        correctTextVisible = false
        wrongTextVisible = false
        correctAnswerVisible = false
        displayResults = false
        buttonText = "Submit"
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
                countDown = 10

                // Once the timer reaches zero, display the answers
                correctTextVisible = selectedCountry == randomCountryName
                wrongTextVisible = selectedCountry != randomCountryName
                correctAnswerVisible = true
                displayResults = true
                buttonText = "Next"
            }
        }

        //Text to display the timer
        Text(
            text =  "Timer :"+ countDown.toString(),
            fontSize = 15.sp,
            fontFamily = Poppins,
            color = Color(0xFF6650a4),
            textAlign = TextAlign.Left
            )

        //To display the flag
        Box(
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(id = randomImageFileName),
                contentDescription = "flag",
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
            )
        }


        //To display the country names or options
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(170.dp)
        ){
            //Getting the values from the hashmap
            val countryNames = flagDetails.values.toList()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //using the item function to display all the countries in the button
                items(countryNames) { country  ->

                    Button(
                        onClick = {
                            selectedCountry = country
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .height(40.dp)
                            .padding(5.dp)
                        ,
                        colors  = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6650a4)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = country,
                            color = Color.White,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }



        Log.d("GuessCountry", "Correct Country: $randomCountryName")
        //To display the results
        if(displayResults){
            //To display the correct text
            if (correctTextVisible) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEFB8C8))
                        .padding(20.dp)

                    ,
                    contentAlignment = Alignment.BottomCenter
                ){
                    Row{
                        Text(
                            text = "CORRECT!",
                            fontFamily = Poppins,
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            //To display the wrong text
            if (wrongTextVisible) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEFB8C8))
                        .padding(20.dp)

                    ,
                    contentAlignment = Alignment.BottomCenter

                ){
                    Row {
                        Text(
                            text = "WRONG!",
                            color = Color.Red,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "CORRECT ANSWER: $randomCountryName",
                            color = Color.Blue,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(20.dp)
        ){
            Button(
                onClick = {
                    Log.d("GuessCountry", "Selected Country: $selectedCountry")
                    if(buttonText == "Submit"){
                        correctTextVisible = selectedCountry == randomCountryName //showing the correct text if the user clicked answer matches with displayed flag name
                        wrongTextVisible = selectedCountry != randomCountryName //showing the wrong text if the answers dont match
                        correctAnswerVisible = true
                        displayResults = true
                        buttonText = "Next" //once the results are displaed changing the button text from submit to next


                    }else{
                        generateNewFlag() //Generating a new flag
                    }

                },
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                ,
                colors  = ButtonDefaults.buttonColors(
                    containerColor = Color.Magenta
                ),
                shape = RoundedCornerShape(5.dp),
            ) {

                Text(
                    text = buttonText,
                    color = Color.White,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
            }
        }
    }
}

//Getting the resource id for the filename
fun getResourceId(fileName : String, context: Context):Int{
    return context.resources.getIdentifier(
        fileName.toLowerCase(Locale.ROOT),
        "drawable",
        context.packageName
    )
}


