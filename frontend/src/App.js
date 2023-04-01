import './style.css';
import {
  createBrowserRouter,
  RouterProvider,
  Outlet
} from 'react-router-dom';
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>
    // children: [
    //   {
    //     path: "/",
    //     element:<Home/>
    //   }
    // ]
  },
  {
    path: "/register",
    element: <Register/>
  },
  {
    path: "/login",
    element: <Login/>
  }
]);

function App() {
  return (
    <div className="App">
      <div className="container">
     <RouterProvider router={router}>

     </RouterProvider>
     </div>
    </div>
  );
}


export default App;
