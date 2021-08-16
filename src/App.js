import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect
} from "react-router-dom";
import Survey from './components/Survey';
import Dashboard from './components/Dashboard';

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route exact path="/">
            <Redirect to="/survey" />
          </Route>
          <Route exact path="/survey" component={Survey}/>
          <Route exact path="/dashboard" component={Dashboard} />  
        </Switch>
      </Router>
    </>
  );
}

export default App;
