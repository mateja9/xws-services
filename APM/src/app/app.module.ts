import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import {
  AgmCoreModule
} from '@agm/core';

import { WelcomeComponent } from './korisnik/welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.services';
import { BrowserModule } from '@angular/platform-browser';
import { SignupComponent } from './login/signup.component';
import { ProfilKorisnikaModule } from './korisnik/profil-korisnika/profil-korisnika.module';
import { ProfilNeRegKorModule } from './korisnik/profilNeRegKor/profilNeRegKor.module';
import { WelcomeModule } from './korisnik/welcome/welcome.module';
import { ProfilNeRegKorComponent } from './korisnik/profilNeRegKor/profilNeRegKor.component';
import { ProfilKorisnikaComponent } from './korisnik/profil-korisnika/profil-korisnika.component';
import { ExploreNistagramComponent } from './korisnik/explore/exploreNistagram.component';
import { Korisnik } from './model/Korisnik';
import { KorisnikService } from './services/korisnik.services';
import { RegistrationNoticeComponent } from './korisnik/registration-notice/registration-notice.component';
import { ForgotPasswordComponent } from './korisnik/forgotpassword/forgotpassword.component';
import { StoriesComponent } from './korisnik/stories/stories.component';
import { FollowComponent } from './korisnik/follow/follow.component';
import { PostsComponent } from './korisnik/posts/posts.components';
import { FavouriteComponent } from './korisnik/favourite/favourite.component';
import { FeedComponent } from './korisnik/feed/feed.component';




@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    SignupComponent,
    ProfilNeRegKorComponent,
    ProfilKorisnikaComponent,
    ExploreNistagramComponent,
    RegistrationNoticeComponent,
    ForgotPasswordComponent,
    StoriesComponent,
    FollowComponent,
    PostsComponent,
    FavouriteComponent,
    FeedComponent




  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'explore', component: ExploreNistagramComponent },
      { path: 'registration-notice', component: RegistrationNoticeComponent },
      { path: 'welcome/:id', component: ProfilNeRegKorComponent },
      { path: 'explore/:id', component: FollowComponent },
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'stories', component: StoriesComponent },
      { path: 'posts', component: PostsComponent },
      { path: 'favourite', component: FavouriteComponent},
      { path: 'feed', component: FeedComponent},
      { path: 'forgotpassword', component: ForgotPasswordComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full' },

      // { path: 'homePage', component: HomeComponent }

    ]),
    FormsModule

  ],
  providers: [LoginService, KorisnikService],
  bootstrap: [AppComponent]
})
export class AppModule { }
