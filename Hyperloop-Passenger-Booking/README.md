# HyperLoop Passenger Booking

1. This app is a pasenger booking app where you can intialize a interconncetion and add passengers.
2. Once paenger are added the person with highest age is sent first to the hyperloop.
3. There are different commands to operate this app ,you can download the app as a zip and use it.

## Commands

1. INIT-Intialize the interconnection for the hyperlooop.
2. ADD_PASSENGER-This command helps to add the passengers.
3. START_POD-This command start the pod(only one person can travel at a time).
4. PRINT_Q-This command print the remaning person waiting in queue in name and age format.

## Commands usage
1. ### INIT
 Example - [INIT 7 A](#)
| Syntax | Description |
| ----------- | ----------- |
| INIT | Initalize the interconnection of the hyperloop |
| 7 | The maximum number of connections that the hyperloop can have |
| A | This is the starting position or station of the hyperloop |

2. ### ADD_PASSENGER
 Example - [ADD_PASSENGER 2](#)
| Syntax | Description |
| ----------- | ----------- |
| ADD_PASSENGER | Initalize the interconnection of the hyperloop |
| 2 | The maximum number of passengers the patient can be added |

3. ### START_POD
 Example - [START_POD 2](#)
| Syntax | Description |
| ----------- | ----------- |
| START_POD | This will start the pod |
| 2 | The maximum number of highest age passengers who have travel in the pods |

4. ### PRINT_Q
 Example - [PRINT_Q](#)
| Syntax | Description |
| ----------- | ----------- |
| PRINT_Q | This will print the remaining passengers in the queue |

# Known Errors
1. #### Command error
   * The command should be given in proper format.
   * only Alphbets should be used in the places of the command and you cannot provide any other symbols for it.
   * order of the commands should be proper.
2. #### Connections error
   * while providing the inter connections please provide within the range of maximum number of connections
     #####   Explanation  
         * if the value is 6 you can provide Alphabets below its ASCII-65 value.
         * A,B,C,D,E,F-0,1,2,3,4,5
         * if let say you provide H which gives value as 7 (ASCII-65) it gives Array index out of bounds error
3. #### 
