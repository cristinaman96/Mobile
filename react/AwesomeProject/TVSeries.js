import React, { Component } from 'react';
import {View, Text, ScrollView, FlatList, Image,StyleSheet,Alert, TouchableOpacity,   RefreshControl} from 'react-native'

export const firebase = require('firebase');

const firebaseConfig = {
        apiKey: "AIzaSyC_LwWpYPaVmCi7NHZkz43UrPBTrnZoxi8",
        authDomain: "awesomeproject-d652a.firebaseapp.com",
        databaseURL: "https://awesomeproject-d652a.firebaseio.com",
        projectId: "awesomeproject-d652a",
        storageBucket: "",
        persistence: true,
};
export const firebaseApp = firebase.initializeApp(firebaseConfig);

export class TVSerie{
    constructor(name, description, rating, image){
        this.id = null;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.image = image;
    }
    setId(newId){
        this.id = newId;
    }
}


var tvseries = [
    {tvserie: new TVSerie( 'Game of Thrones','Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.', 5,require('./img/game_of_thrones.jpg'))},
    {tvserie: new TVSerie('House','An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.',7,require('./img/house.jpg'))},
    {tvserie: new TVSerie('Sherlock', 'A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London',6,require('./img/sherlock.jpg'))},
    {tvserie: new TVSerie( 'Stranger Things', 'When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.',9, require('./img/stranger_things.jpg'))},
    {tvserie: new TVSerie( 'The Walking Dead', 'Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.',4, require('./img/the_walking_dead.jpg'))},

];

function getTvSeries(name, description,rating, image) {
    return{name, description, rating, image};
}

export class TVSeries extends Component{
    static navigationOption = {title : 'TV Series',};

    constructor(props){
        super(props);
        this._onRefresh = this._onRefresh.bind(this);
        this.state = {refreshing: false, newtvseries:[], loading: true};
        this.items = this.getRef().child('tvseries');

        this.currentuser = firebase.auth().currentUser.uid;
        console.log("USEEEERRR: "+ this.currentuser);

        this.user = this.getRef().child('users');

        console.log("^^^^^^^^^^^^^^ " + this.user + "&&&&&" );

        // this.items.push(tvseries[0]);
        // this.items.push(tvseries[1]);
        // this.items.push(tvseries[2]);
        // this.items.push(tvseries[3]);
        // this.items.push(tvseries[4]);

    }

    getRef(){
        return firebaseApp.database().ref();
    }

    _onRefresh() {
        this.setState({refreshing:true});
        this.setState({refreshing:false});
        this.getItems(this.items);
    }

    getItems(items){
        items.on('value',(snap)=>{
            var items = [];
            snap.forEach((child) =>{
                items.push({ tvserie: child.val().tvserie,
                        key: child.key});
            });
            console.log(items);
            this.setState({newtvseries: items});
            this.setState({loading:false});
        });

    }

    componentWillMount() {
        this.getItems(this.items);
    }


    delete(key, username){
        if(username === "user"){
            Alert.alert('','Only admin users can delete TV series',[{text:'OK',onPress: () => console.log("!!!!!")}], {cancelable:false})
        }

        if(username === "admin"){
            Alert.alert('INFO','Are you sure you want to delete this TV serie?', [{text:'Yes', onPress:() =>{this.items.child(key).remove();
                this._onRefresh();}},{text:'No', onPress:()=>console.log("##########") }], {cancelable: false})
        }
    }

    showAlert(name, key){
        this.getRef().child("users").child(this.currentuser).once('value').then(snapshot =>{
            var username = (snapshot.val());
            console.log("******************" + this.currentuser + "&&&&&&&&&&&&&&&");

            console.log("&&&&&&&&&&" + username + "&&&&&&&&&&&&&&&");
            this.delete(key,username);
        })
    }


    render() {
        const {navigate} = this.props.navigation;
        if(this.state.loading == false){
            return(
                <View style={styles.container}>
                    <View style={styles.header}>
                        <Text style={styles.headerText}>TV Series</Text>
                    </View>
                    <FlatList
                        refreshControl={
                            <RefreshControl
                                refreshing={this.state.refreshing}
                                onRefresh={this._onRefresh.bind(this)}
                            />
                        }
                        data={this.state.newtvseries}
                        renderItem={
                            ({item}) =>
                                <ScrollView>
                                    <View style={styles.linearView}>
                                        <Image style={{height: 70, width: 50, resizeMode: 'contain'}} source={item.tvserie.image}/>
                                        <Text style={styles.item}
                                              onPress={() => navigate('Details', {tvserie:item.tvserie,key:item.key,refresh: this._onRefresh})}> {item.tvserie.name} </Text>
                                        <View style={styles.deleteView}>
                                            <TouchableOpacity style={styles.deleteButton}
                                                              onPress={ () => { this.showAlert(item.tvserie.name,item.key);
                                                                  console.log("########", item.key)}}>
                                                <Text style={styles.proposeButtonText}> X </Text>
                                            </TouchableOpacity>
                                        </View>
                                    </View>
                                </ScrollView>
                            // ({item}) => <View><Text>dsjbbgkaagdg</Text></View>
                        }
                        extraData = {this.state.newtvseries}
                    />
                    <View style={styles.footer}>
                        <TouchableOpacity  style={styles.proposeButton} onPress = {() => {navigate('ProposeTVSeries', {refresh: this._onRefresh})}}>
                            <Text style={styles.proposeButtonText}> Propose a TV series</Text>
                        </TouchableOpacity >

                        <TouchableOpacity style={styles.addButton} onPress = {() =>{


                            this.getRef().child("users").child(this.currentuser).once('value').then(snapshot => {
                                var username = (snapshot.val());
                                console.log("-----------" + username);
                                if (username === "admin") {
                                    navigate('AddTVSeries', {refresh: this._onRefresh})
                                }

                                if (username === "user") {
                                    console.log(username);
                                    Alert.alert('', 'Only admin users can add TV series',
                                        [
                                            {
                                                text: 'OK',
                                                onPress: () => console.log("!!!!")
                                            }
                                        ],
                                        {cancelable: false}
                                    )
                                }
                            });
                        }
                        }>
                            <Text style={styles.proposeButtonText}> Add a TV series</Text>
                        </TouchableOpacity>
                    </View>
                    <View>
                        <TouchableOpacity style={styles.proposeButton} onPress={() =>
                            navigate('Home')}>
                            <Text style={styles.proposeButtonText}> Sign out </Text>
                        </TouchableOpacity>
                    </View>
                </View>);
        }
        else{
             return(
                 <View>
                     <View style={styles.header}>
                         <Text style={styles.headerText}>TV Series</Text>
                     </View>
                     <Text> Content Is Loading... </Text>
                 </View>
             )

         }
    }
}

const styles = StyleSheet.create({
    container: {
        height:650
    },
    header:{
        backgroundColor: '#01DF01',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomWidth: 10,
        borderBottomColor: '#ddd',

    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
    headerText:{
        color: 'white',
        fontSize: 18,
        padding: 26,
    },
    footer: {
        alignItems: 'center',
        top:20,
        // flexDirection:'row',
        left: 0,
        right: 0,
    },
    proposeButton: {
        backgroundColor: '#01DF01',
        borderColor: '#ccc',
        justifyContent: 'center',
        marginLeft:50,
        marginBottom:45,
        marginRight:7
    },
    deleteButton: {
        backgroundColor: '#01DF01',
        //alignSelf:'flex-end',
        borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
        justifyContent: 'center',
        marginLeft:50,
        marginBottom:45,
        marginRight:7
    },
    deleteView:{
        flex: 1, flexDirection: 'row', justifyContent: 'flex-end'
    },
    addButton:{
        backgroundColor: '#01DF01',
        //borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
        justifyContent: 'center',
        marginRight:50,
        marginRight:7,
        marginBottom:45
    },
    proposeButtonText: {
        color:'#fff',
        fontSize:24,
    },
    linearView: {
        flexDirection:'row',
        padding:8,

    },
    TVSeriesTitle:{
        color:'#01DF01',
        fontSize:25,
        textAlign:'center',
    },
    detailedImage: {
        height:220,
        width: 200,
        resizeMode: 'contain',
        marginBottom:28,
        marginTop:28
    }
});
