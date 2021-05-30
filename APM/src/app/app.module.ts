import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import {
  AgmCoreModule
} from '@agm/core';

import { WelcomeComponent } from './home/welcome.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.services';



@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
  
  ],
  imports: [
  
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'login', component: LoginComponent },
    

      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full'},
      
      // { path: 'homePage', component: HomeComponent }

    ]),
    FormsModule,
    
  ],
  providers:[LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
