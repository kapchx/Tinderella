import { UpdateProfileComponent } from './users/profile/update/update-profile.component';
import { ChatComponent } from './matches/chat/chat.component';
import { ListComponent } from './users/list/list.component';
import { ListMatchesComponent } from './matches/list-matches/list-matches.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PrivateRoute } from './core/private-route';

import { LoginComponent } from './users/login/login.component';
import { RegisterComponent } from './users/register/register.component';
import { ProfileComponent } from './users/profile/profile.component';


const routes: Routes = [
    { path: 'users/register', component: RegisterComponent },
    { path: 'users/login', component: LoginComponent },
    { path: 'users/profile/update', component: UpdateProfileComponent },
    { path: 'users/profile', component: ProfileComponent },
    { path: 'users/profile/:id', component: ProfileComponent },
    { path: 'matches/list', component: ListMatchesComponent, canActivate: [PrivateRoute] },
    { path: 'users/list', component: ListComponent, canActivate: [PrivateRoute] },
    { path: 'matches/list', component: ListMatchesComponent, canActivate: [PrivateRoute] },
    { path: 'matches/chat/:id', component: ChatComponent, canActivate: [PrivateRoute] }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class CarRoutesModule {

}
