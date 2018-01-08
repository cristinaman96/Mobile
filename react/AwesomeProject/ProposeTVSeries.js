import React, {Component} from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    TextInput,
    Linking,
    StyleSheet
} from 'react-native';
import moment from 'moment';



export class ProposeTVSeries extends Component{
    render() {
        return (
            <View>
                <Text> Title: </Text>
                <TextInput onChangeText = {(title) => this.setState({title})}/>
                <Text> Name: </Text>
                <TextInput onCgange = {(name) => this.setState({name})}/>
                <ToucableOpacity  style={styles.proposeButton} >
                    <Text  style={styles.proposeButtonText} onPress = {() => { receiver = "man.cristina96@yahoo.com";
                        subject = "Proposal of a new TV series";
                        body = "Title: " + this.state.title + "\n"+
                               "Name: " + this.state.name;
                        all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body;
                        Linking.openURL(all)}}> Propose </Text>
                </ToucableOpacity>
            </View>
        );
    }
}

