import { useState } from 'react'
import './App.css'
import axios from 'axios';

function App() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  
  const submitValue = () => {
    console.log(`Username: ${username}, Password: ${password}`);
    axios.post('http://localhost:8080/v1/users/register', {username, password})
    .then((response) => console.log(response))
    .catch((err) => console.log(err))
  }

  return (
    <>
      <div className='container d-flex flex-column justify-content-center align-items-center p-5'>
        <form className='w-75'>
          <div className="form-group">
            <label htmlFor="username" className='mb-2'>Email address</label>
            <input type="text" 
              className="form-control mb-2" 
              id="username" 
              placeholder="Enter name" 
              value={username}
              onChange={(e) => setUsername(e.target.value)} />
          </div>
          <div className="form-group">
            <label htmlFor="password" className='mb-2'>Password</label>
            <input type="text" 
              className="form-control mb-2" 
              id="password" 
              placeholder="Password" 
              value={password}
              onChange={(e) => setPassword(e.target.value)} />
          </div>
          <button type="button" onClick={submitValue} className="btn btn-primary mb-2">Submit</button>
        </form>
      </div>
    </>
  )
}

export default App
