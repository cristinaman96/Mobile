import React, {Component} from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    TextInput,
    Linking,
    StyleSheet
} from 'react-native';



export class ProposeTVSeries extends Component{
    render() {
        return (
            <View>
                <Text> Title: </Text>
                <TextInput onChangeText = {(title) => this.setState({title})}/>
                <Text> Name: </Text>
                <TextInput onCgange = {(name) => this.setState({name})}/>
                <TouchableOpacity   style={styles.proposeButton} >
                    <Text  style={styles.proposeButtonText} onPress = {() => { receiver = "man.cristina96@yahoo.com";
                        subject = "Proposal of a new TV series";
                        body = "Title: " + this.state.title + "\n"+
                               "Name: " + this.state.name;
                        all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body;
                        Linking.openURL(all)}}> Propose </Text>
                </TouchableOpacity >
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


