import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ScrollView,
    Image,
    RefreshControl,
    TextInput, Picker
} from 'react-native';
import {firebaseApp} from './TVSeries'
import {TVSerie} from "./TVSeries";

export class AddTVSeries extends Component{
    state = {
        newName:'',
        newDescription:'',
        rating:'1'
    };

    print(){
        console.log(this.state.newName);
        console.log(this.state.newDescription);
        console.log(this.state.newRating);
    }

    save(){
        const items = firebaseApp.database().ref().child('tvseries');
        console.log("### ###");

        items.push({tvserie: new TVSerie(this.state.newName, this.state.newDescription, this.state.rating, require('./img/house.jpg' ))});
    }

    render(){
        const {params} = this.props.navigation.state;
        const {goBack} = this.props.navigation;
        return(
            <View>
                <ScrollView>
                    <View style={{justifyContent: 'center',alignItems: 'center',}}>

                        <Text style = {styles.TVSeriesTitle} > Add a TV serie</Text>

                        <Picker
                            style={{width: 100}}
                            selectedValue={this.state.rating}
                            onValueChange={(itemValue) => this.setState({rating: itemValue})}>
                            <Picker.Item label="1" value="1" />
                            <Picker.Item label="2" value="2" />
                            <Picker.Item label="3" value="3" />
                            <Picker.Item label="4" value="4" />
                            <Picker.Item label="5" value="5" />
                            <Picker.Item label="6" value="6" />
                            <Picker.Item label="7" value="7" />
                            <Picker.Item label="8" value="8" />
                            <Picker.Item label="9" value="9" />
                            <Picker.Item label="10" value="10" />
                            {/*<Text style = {styles.text}>{this.state.newRating}</Text>*/}


                        </Picker>

                        <TextInput style= {{alignSelf: 'stretch'}} placeholder="  Title" onChangeText={(text) => this.setState({newName: text})}/>
                        <TextInput style= {{alignSelf: 'stretch'}} placeholder="  Description" onChangeText={(text) => this.setState({newDescription: text})}/>
                        {/*<TextInput style={{alignSelf: 'stretch'}} placeholder ="  Rating" multiline={true} onChangeText={(text) => this.setState({newRating: text})} />*/}


                    </View>
                </ScrollView>
                <View>
                    <TouchableOpacity style={styles.proposeButton} onPress = {
                        ()=>{
                            this.save();
                            // params.refresh();
                            goBack();
                        }
                    }>
                        <Text style={styles.proposeButtonText}>Save</Text>
                    </TouchableOpacity>

                </View>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        //flex: 1,
        height:650
        //paddingTop: 22
    },
    header:{
        backgroundColor: '#01DF01',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomWidth: 10,
        borderBottomColor: '#ddd',
        //marginTop:-40,

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
        //position: 'absolute',
        alignItems: 'center',
        //marginBottom:-30,
        top:20,
        flexDirection:'row',
        left: 0,
        right: 0,
    },
    proposeButton: {
        backgroundColor: '#01DF01',
        //borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
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
