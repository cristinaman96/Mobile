import React from 'react'
import {View, Text, ScrollView, FlatList, Image, TouchableOpacity,  AsyncStorage, RefreshControl} from 'react-native'

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


var tvSeries = [
    {key:1, tvserie: new TVSerie( 'Game of Thrones','Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.', 5,require('./img/game_of_thrones.jpg'))},
    {key:2, tvserie: new TVSerie('House','An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.',7,require('./img/house.jpg'))},
    {key:3, tvserie: new TVSerie('Sherlock', 'A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London',6,require('./img/sherlock.jpg'))},
    {key:4, tvserie: new TVSerie( 'Stranger Things', 'When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.',9, require('./img/stranger_things.jpg'))},
    {key:5, tvserie: new TVSerie( 'The Walking Dead', 'Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.',4, require('./img/the_walking_dead.jpg'))},

];

function getTvSeries(name, description,rating, image) {
    return{name, description, rating, image};
}

export default class TVSeries extends React.Component{
    static navigationOption = {title : 'TV Series',};

    constructor(props){
        super(props);
        this._onRefresh = this._onRefresh.bind(this);
        this.state = {refreshing: false, newtvseries:[], loading: true};
    }

    async findByName(name){
        let response = await AsyncStorage.getItem('@MyStore:key');
        let tvSeries = JSON.parse(response);
        var count = tvSeries.length;

        for (var i = 0; i < count; i ++)
        {
            if (tvSeries[i].tvserie.name === name){
                return i;
            }
        }
        return -1;
    }

    _onRefresh() {
        this.setState({refreshing:true});
        this.setState({refreshing:false});
        this.retrieveContent();
    }

    retrieveContent(){
        AsyncStorage.getItem('@MyStore:key').then((value) =>{
            this.setState({newtvseries: JSON.par(value)});
            console.log("NEW: ", this.state.newtvseries);
        }).catch((error) => {
            console.log("Error: " + error);
        });
    }

    getItems(){
        AsyncStorage.getItem('@MyStore:key').then((value) => {
            this.setState({newtvseries : JSON.parse(value)});
            console.log("Retrieved data: " + this.state.newtvseries);
            var count = this.state.newtvseries.length;
            for(var i=0; i<count; i++){
                console.log(this.state.newtvseries[i].tvserie.image);
            }
            this.setState({loading:false});
        }).catch((error) => {
            console.log("Error: " + error);
        });
    }

    componentWillMount() {
        this.getItems();
    }



    render() {
        const {navigate} = this.props.navigation;
        if(this.state.loading == false){
            return(
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
                        data={this.state.newtvseries}
                        renderItem={
                            ({item}) =>
                                <View>
                                    <View >
                                        <Image style={{height: 70, width: 50, resizeMode: 'contain'}} source={item.tvserie.image}/>
                                        <Text
                                              onPress={() => navigate('Details', {tvserie: item.tvserie,refresh: this._onRefresh()})}> {item.tvserie.title} </Text>
                                    </View>
                                </View>
                            // ({item}) => <View><Text>dsjbbgkaagdg</Text></View>
                        }
                    />
                    {/*<View>*/}
                        {/*<TouchableOpacity onPress = {() => navigate('ProposeTVSeries')}>*/}
                            {/*<Text> Propose a TV series</Text>*/}
                        {/*</TouchableOpacity>*/}

                        {/*<TouchableOpacity onPress = {() => navigate('AddTVSeries')}>*/}
                            {/*<Text> Add a TV series</Text>*/}
                        {/*</TouchableOpacity>*/}
                    {/*</View>*/}
                </View>);
        }
        else{
             return(
                 <View> <Text> Content Is Loading... </Text> </View>
             )

         }
    }
}