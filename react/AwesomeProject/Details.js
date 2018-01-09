import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    ScrollView,
    Image,
    TextInput,
    Button,
    AsyncStorage,
    TouchableOpacity,
    processColor,

} from 'react-native';
import {StackNavigator, SafeAreaView} from 'react-navigation';
import {firebaseApp} from './TVSeries'


export class Details extends Component{
    static navigationOptions = {
        header:null,
    };

    updateTVSeriesDescription(index, description){
        const items = firebaseApp.database().ref().child('tvseries');
        console.log("@@@@@@@@@@@@" + index + " " + description);
        items.child(index).child("tvserie").child("description").set(description);
    }


    render() {
        const {navigate} = this.props.navigation;
        const {params} = this.props.navigation.state;
        const {goBack} = this.props.navigation;

        var tvserie = params ? params.tvserie : "<undefined>";
        var key = params? params.key : "<undefined>";

        return (
            <View>
            <ScrollView>
                <View style={{
                    justifyContent: 'center',
                    alignItems: 'center',
                }}>
                    <Text style={styles.TVSeriesTitle}> {tvserie.name} </Text>
                    <Image source={tvserie.image}/>
                    <Text style = {{height: 160, width: 350, marginTop:10 }}> {tvserie.rating} </Text>

                    <ScrollView>
                        <TextInput style={{height: 160, width: 350, marginTop:10 }} multiline={true}onChangeText={(text) => this.setState({newDescription: text})}> {tvserie.description} </TextInput>
                    </ScrollView>
                </View>
            </ScrollView>
                <View>
                    <TouchableOpacity style={styles.proposeButton} onPress={
                        () => {
                            console.log("##############################");
                            if (this.state.newDescription) {
                                this.updateTVSeriesDescription(key, this.state.newDescription);
                            }
                            params.refresh();
                            goBack();
                        }
                    }>
                        <Text style={styles.proposeButtonText}>Save changes </Text>
                    </TouchableOpacity>
                </View>
            </View>
        );

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
