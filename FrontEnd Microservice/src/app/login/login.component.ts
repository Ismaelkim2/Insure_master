
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'; // Import FormBuilder
import { LoginService } from '../login.service'; // Import the LoginService

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup; 

  constructor(private fb: FormBuilder, private loginService: LoginService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], 
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value; 
      const username = email; 
  
      this.loginService.login(username, password).subscribe(
        response => {
          console.log('Login successful', response);
        },
        error => {
          console.error('Login failed', error);
        }
      );
    } else {
      console.log('Form is not valid');
    }
  }
  
}