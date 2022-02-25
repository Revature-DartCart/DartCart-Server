import React, { useState } from 'react'
import { User } from '../model/User'
import userService from '../services/user.service';
import { Customer } from '../model/Customer'

export function Register() {


	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
    const [repassword, setRepassword] = useState("");
	const [first_name, setFirstName] = useState("");
	const [last_name, setLastName] = useState("");
	const [email, setEmail] = useState("");
	const [phone, setPhone] = useState("");
	//Registration is auto-created
	const [location, setLocation] = useState("");

	//Error handling
	const [error, setError] = useState("");
	const errorHandle = async () => {
		if (username === "") {
			setError("Error: please enter a username")
		}
		else if (password === "") {
			setError("Error: please enter a password")
		}
		//More TBI
		else {
			//Create User and Customer
			let user = new User(username, password, first_name, last_name, email, phone)
			await userService.postUser(user);
			let customer = new Customer(userService.getIdByUsername, location);
			await customerService.postCustomer(customer);
			window.alert("Account created");
		}
	}

	return (
		<>
            <section className="vh-200">
                <div className="container py-5 h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-8">
                            <div className="card shadow-2-strong" style={{ borderRadius: '1rem' }}>
                                <div className="card-body p-5 text-center">

                                    <h3 className="mb-5">Register Form</h3>
                                    {
                                        (alert)
                                        &&
                                        (<div class="alert alert-danger" role="alert">
                                            {alert}
                                        </div>)
                                    }

                                    <div className="row">
                                        <div className="form-outline mb-4 col-6">
                                            <input type="text" placeholder="username" id="typeEmailX-2" className="form-control form-control-lg" onChange={(e) => setUsername(e.target.value)} />
                                        </div>
                                        <div className="form-outline mb-4 col-6">
                                            <input type="text" placeholder="First name" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setFirstName(e.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="form-outline mb-4 col-6" >
                                            <input type="password" placeholder="password" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setPassword(e.target.value)} />
                                        </div>
                                        <div className="form-outline mb-4 col-6">
                                            <input type="text" placeholder="Last name" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setLastName(e.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="form-outline mb-4 col-6">
                                            <input type="password" placeholder="Confirm password" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setRepassword(e.target.value)} />
                                        </div>
                                        <div className="form-outline mb-4 col-6">
                                            <input type="email" placeholder="Email" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setEmail(e.target.value)} />
                                        </div>
                                    </div>

                                    <div className="row">
                                        <div className="form-outline mb-4 col-6">
                                            <input type="text" placeholder="Location" id="typePasswordX-2" className="form-control form-control-lg" onChange={(e) => setLocation(e.target.value)} />
                                        </div>
                                        <div className="form-outline mb-4 col-6">
                                            <input type="phone" placeholder="Phone Number" onChange={(e) => setPhone(e.target.value)} id="typePasswordX-2" className="form-control form-control-lg" />
                                        </div>
                                    </div>









                                    <button className="btn btn-primary btn-lg btn-block" onClick={handleSubmit}>Register</button>


                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}