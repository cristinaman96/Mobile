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

export class Details extends Component{
    static navigationOptions = {
        header:null,
    };
    render() {
        const {state} = this.props.navigation;
        var tvSerie = state.params ? state.params.tvSerie : "<undefined>";
        return (
            <ScrollView>
                <View style={{
                    justifyContent: 'center',
                    alignItems: 'center',
                }}>
                    <Text > {tvSerie.title} </Text>
                    <Image source={tvSerie.image}/>
                    <ScrollView>
                        <TextInput style={{height: 300, width: 350, marginTop:10 }} multiline={true}> {tvSerie.description} </TextInput>
                    </ScrollView>
                </View>
            </ScrollView>
        );

    }
}
