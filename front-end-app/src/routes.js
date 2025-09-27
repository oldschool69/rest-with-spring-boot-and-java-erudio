import {BrowserRouter, Route, Switch} from 'react-router-dom';

import Login from "./pages/Login";
import Books from './pages/Books';
import NewBook from './pages/NewBook';

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}></Route>
                <Route path="/books" component={Books}></Route> 
                <Route path="/book/new" component={NewBook}></Route>
            </Switch>
        </BrowserRouter>
    );
}