import {BrowserRouter, Route, Switch} from 'react-router-dom';

import Login from "./pages/Login";
import Book from './pages/Book';

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}></Route>
                <Route path="/book" component={Book}></Route> 
            </Switch>
        </BrowserRouter>
    );
}