/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react';
import { AppRegistry,
    FlatList,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ScrollView,
    Image,
    TextInput,
    Linking,
    Button} from 'react-native';
import {StackNavigator} from 'react-navigation'
import {TVSeries} from "./TVSeries"
import {ProposeTVSeries} from "./ProposeTVSeries";
import {Details} from "./Details";
import {AddTVSeries} from "./AddTVSeries";
import {Login} from "./Login";
// import {Chart} from './Charts'


//
// var tvSeries=[
//     {
//         key:'1',
//         title: 'Game of Thrones',
//         description: 'Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.',
//         image: require('./img/game_of_thrones.jpg')},
//     {
//         key:'2',
//         title: 'House',
//         description: 'An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.',
//         image: require('./img/house.jpg')},
//     {
//         key:'3',
//         title: 'Sherlock',
//         description: 'A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London',
//         image: require('./img/sherlock.jpg')},
//     {
//         key:'4',
//         title: 'Stranger Things',
//         description: 'When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.',
//         image: require('./img/stranger_things.jpg')},
//     {
//         key:'5',
//         title: 'The Walking Dead',
//         description: 'Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.',
//         image: require('./img/the_walking_dead.jpg')},
// ];
//
//
// class TVSeriesList extends Component{
//
//     // onPress = () => {navigate('ProposePage')}
//     render(){
//         const {navigate} = this.props.navigation;
//         return(
//             <View style = {styles.container}>
//                 <View style = {styles.header}>
//                     <Text style = {styles.headerText}>TV Series</Text>
//                 </View>
//                 <FlatList
//                     data = {tvSeries}
//                     renderItem={
//                         ({item}) => <ScrollView>
//                             <View style={styles.linearView} >
//                                 <Image style= {{ height:70, width: 50, resizeMode: 'contain' }} source={item.image}/>
//                                 <Text style={styles.item} onPress = {() => navigate('Details',{tvSerie : item})}> {item.title} </Text>
//                             </View>
//                         </ScrollView>
//                         // ({item}) => <View><Text>dsjbbgkaagdg</Text></View>
//                     }
//                 />
//                 <View style = {styles.footer}>
//                     <TouchableOpacity style={styles.proposeButton} onPress = {() => navigate('ProposePage')}>
//                         <Text style={styles.proposeButtonText}> Propose a new TV series</Text>
//                     </TouchableOpacity>
//                 </View>
//             </View>
//         );
//     }
// }


// class Details extends Component{
//     static navigationOptions = {
//         header:null,
//     };
//     render() {
//         const {state} = this.props.navigation;
//         var tvSerie = state.params ? state.params.tvSerie : "<undefined>";
//         return (
//             <ScrollView>
//                 <View style={{
//                     justifyContent: 'center',
//                     alignItems: 'center',
//                 }}>
//                     <Text > {tvSerie.title} </Text>
//                     <Image source={tvSerie.image}/>
//                     <ScrollView>
//                         <TextInput style={{height: 300, width: 350, marginTop:10 }} multiline={true}> {tvSerie.description} </TextInput>
//                     </ScrollView>
//                 </View>
//             </ScrollView>
//         );
//
//     }
// }
//
// class ProposePage extends Component{
//     render() {
//         return (
//             <View>
//                 <Text> Title: </Text>
//                 <TextInput onChangeText = {(title) => this.setState({title})}/>
//                 <Text> Name: </Text>
//                 <TextInput onCgange = {(name) => this.setState({name})}/>
//                 <ToucableOpacity  style={styles.proposeButton} >
//                     <Text  style={styles.proposeButtonText} onPress = {() => { receiver = "man.cristina96@yahoo.com";
//                         subject = "Proposal of a new TV series";
//                         body = "Title: " + this.state.title + "\n"+
//                                "Name: " + this.state.name;
//                         all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body;
//                         Linking.openURL(all)}}> Propose </Text>
//                 </ToucableOpacity>
//             </View>
//         );
//     }
// }
//
//
// const styles = StyleSheet.create({
//     container: {
//         flex: 1,
//         backgroundColor: '#01DF01',
//         alignItems: 'center',
//         justifyContent: 'center',
//     },
//     header:{
//         backgroundColor: '#01DF01',
//         alignItems: 'center',
//         justifyContent: 'center',
//         borderBottomWidth: 10,
//         borderBottomColor: '#ddd',
//         //marginTop:-40,
//
//     },
//     item: {
//         padding: 10,
//         fontSize: 18,
//         height: 44,
//     },
//     headerText:{
//         color: 'white',
//         fontSize: 18,
//         padding: 26,
//     },
//     footer: {
//         position: 'absolute',
//         alignItems: 'center',
//         bottom: 0,
//         left: 0,
//         right: 0,
//     },
//
//     linearView: {
//         flexDirection:'row',
//         padding:8,
//     },
//
//     detailedImage: {
//         height:220,
//         width: 200,
//         resizeMode: 'contain',
//         marginBottom:28,
//         marginTop:28
//     },
//     proposeButton:{
//         backgroundColor: '#01DF01',
//         //borderRadius: 30,
//         borderColor: '#ccc',
//         alignItems: 'center',
//         justifyContent: 'center',
//         marginBottom:45
//     },
//     proposeButtonText: {
//         color:'#fff',
//         fontSize:24,
//     }
//
// });

const NavigationApp = StackNavigator({
    Home: {screen: Login},
    TVSeries: {screen: TVSeries},
    ProposeTVSeries: {screen: ProposeTVSeries},
    Details: {screen: Details},
    AddTVSeries: {screen: AddTVSeries},
    // Charts: {screen: Chart}
}

);

export default class App extends Component<> {
    render() {
        return <NavigationApp/>;
    }
}

// skip this line if using Create React Native App
AppRegistry.registerComponent('AwesomeProject', () => App);
