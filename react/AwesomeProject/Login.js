import React, { Component } from 'react';
import {View, Text, Button, TextInput, StyleSheet, ActivityIndicator} from 'react-native';
import firebase from 'firebase';

export class Login extends Component{
    state = {
        email:'',
        password:'',
        error:''
    };

    render(){
        const {navigate} = this.props.navigation;
        return(
          <View>
              <View style={styles.header}>
                  <Text style={styles.headerText}> TV Series</Text>
              </View>

              <TextInput
                  label='Enter Email Address: '
                  placeholder='email'
                  value={this.state.email}
                  onChangeText={email => this.setState({ email })}
              />
              <TextInput
                  label='Password: '
                  autoCorrect={false}
                  placeholder='*****'
                  secureTextEntry
                  value={this.state.password}
                  onChangeText={password => this.setState({ password })}
              />

              <Text style={styles.errorTextStyle}>{this.state.error}</Text>


              <Button style={styles.proposeButton} color="#E91E63" onPress={ () => {
                  const { email, password } = this.state;
                  firebase.auth().signInWithEmailAndPassword(email, password)
                      .then(() => { this.setState({ error: '', email:"", password:"" });navigate('TVSeries')})
                      .catch(() => { console.log("!!!!!!!!!!!!!!")
                          this.setState({ error: 'Authentication failed.'});
                      });
                  }
              }
                      title="Log in" />

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
    errorTextStyle: {
        color: '#e69e35',
        alignSelf: 'center',
        paddingTop: 10,
        paddingBottom: 10
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
