import { useState } from 'react'
import './App.css'
import { use } from 'react'

function App() {

  const [data, setData] = useState({
    username: '',
    password: ''
  });



  return (
    <>
      <div className='container d-flex flex-column justify-content-center align-items-center p-5'>
        <form className='w-75' onSubmit={handleSubmit}>
          <div class="form-group">
            <label for="name" className='mb-2'>Email address</label>
            <input type="text" className="form-control mb-2" id="name" placeholder="Enter name" />
          </div>
          <div class="form-group">
            <label for="password" className='mb-2'>Password</label>
            <input type="password" className="form-control mb-2" id="password" placeholder="Password" />
          </div>
          <button type="submit" className="btn btn-primary mb-2">Submit</button>
        </form>
      </div>
    </>
  )
}

export default App
