import React from 'react'
import {StyleSheet, View, Text,ScrollView, FlatList, Image, RefreshControl} from 'react-native'

export class TVSerie{
    constructor(name, description, image){
        this.name = name;
        this.description = description;
        this.image = image;
    }
}

function getTvSeries(name, description, image) {
    return{name, description, image};
}

export default class TVSeries extends React.Component{
    static navigationOption = {title : 'TV Series',};

    constructor(props){
        super(props);
        this._onRefresh = this._onRefresh.bind(this);
        this.state = {refreshing: false,};
        tvSeries = [
            new TVSerie( 'Game of Thrones','Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.', require('./img/game_of_thrones.jpg')),

        ];

    }
    _onRefresh() {
        this.setState({refreshing:true});
        this.setState({refreshing:false});
    }

    render() {
        const {navigate} = this.props.navigation;
        return (
            <View >
                <View >
                    <Text >TV Series</Text>
                </View>
                <FlatList
                    refreshControl={
                        <RefreshControl
                            refreshing={this.state.refreshing}
                            onRefresh={this._onRefresh.bind(this)}
                        />
                    }
                    data={tvSeries}
                    renderItem={
                        ({item}) => <ScrollView>
                            <View >
                                <Image style={{height: 70, width: 50, resizeMode: 'contain'}} source={item.image}/>
                                <Text
                                      onPress={() => navigate('Details', {tvSerie: item})}> {item.title} </Text>
                            </View>
                        </ScrollView>
                        // ({item}) => <View><Text>dsjbbgkaagdg</Text></View>
                    }
                />
            </View>);
    }
}