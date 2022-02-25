import React, {useState} from 'react'
import { User } from '../model/User';
import userService from '../services/user.service';

export function RegisterComponent() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repassword, setRepassword] = useState("");
    //const [status, setStatus] = useState("PUBLIC");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [role, setRole] = useState("DEVELOPER");

    const handleSubmit = async () => {
        let user = new User(username,password,firstname,lastname,email,phone);
        console.log(user);
        userService.postUser(user);
        if(role==="DEVELOPER"){
            userService.postDeveloper(user.username);
        }
    }

    return (
        <>



            <section class="vh-200">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-8">
                            <div class="card shadow-2-strong" style={{ borderRadius: '1rem' }}>
                                <div class="card-body p-5 text-center">

                                    <h3 class="mb-5">Register Form</h3>
                                    <div class="row">
                                        <div class="form-outline mb-4 col-6">
                                            <input type="text" placeholder="username" id="typeEmailX-2" class="form-control form-control-lg" onChange={(e) => setUsername(e.target.value)}/>
                                        </div>
                                        <div class="form-outline mb-4 col-6">
                                        <input type="text" placeholder="First name" id="typePasswordX-2" class="form-control form-control-lg" onChange={(e) => setFirstname(e.target.value)}/>
                                    </div>
                                    </div>
                                    <div class="row">
                                    <div class="form-outline mb-4 col-6" >
                                        <input type="password" placeholder="password" id="typePasswordX-2" class="form-control form-control-lg" onChange={(e) => setPassword(e.target.value)}/>
                                    </div>
                                    <div class="form-outline mb-4 col-6">
                                        <input type="text" placeholder="Last name" id="typePasswordX-2" class="form-control form-control-lg" onChange={(e) => setLastname(e.target.value)}/>
                                    </div>
                                    </div>
                                    <div class="row">
                                    <div class="form-outline mb-4 col-6">
                                        <input type="password" placeholder="Confirm password" id="typePasswordX-2" class="form-control form-control-lg" onChange={(e) => setRepassword(e.target.value)}/>
                                    </div>
                                    <div class="form-outline mb-4 col-6">
                                        <input type="email" placeholder="Email" id="typePasswordX-2" class="form-control form-control-lg" onChange={(e) => setEmail(e.target.value)}/>
                                    </div>
                                    </div>
                                    
                                    <div class="row">
                                    <div class="form-outline mb-4 col-6">
                                        <select class="form-control form-control-lg" onChange={(e) => setRole(e.target.value)} >
                                            <option value="DEVELOPER">Developer</option>
                                            <option value="RECRUITER">Recruiter</option>
                                        </select>
                                    </div>
                                    <div class="form-outline mb-4 col-6">
                                        <input type="phone" placeholder="Phone Number" onChange={(e) => setPhone(e.target.value)} id="typePasswordX-2" class="form-control form-control-lg" />
                                    </div>
                                    </div>
                                    

                                    

                                    

                                    

                                    
                                    <button class="btn btn-primary btn-lg btn-block" onClick={handleSubmit}>Register</button>


                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}
