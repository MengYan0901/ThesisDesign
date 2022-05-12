import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Country from '../views/Country.vue'
import Site from '../views/Site.vue'
import SignIn from '../views/SignIn.vue'
import SignUp from '../views/SignUp.vue'
import User from '../views/User.vue'
import Admin from '../views/Admin.vue'
import Search from '../views/Search.vue'
import Tip from '../views/Tip.vue'
import Ticket from '../views/Ticket.vue'
import ShowTicket from '../views/ShowTicket.vue'
import Route from '../views/Route.vue'
import Comment from '../views/Comment.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/country',
    name: 'Country',
    component: Country
  },
  {
    path: '/site',
    name: 'Site',
    component: Site
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/user',
    name: 'User',
    component: User
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/search/:query',
    name: 'Search',
    component: Search,
    props: true
  },
  {
    path: '/tip',
    name: 'Tip',
    component: Tip,
  },
  {
    path: '/ticket/showticket',
    name: 'ShowTicket',
    component: ShowTicket,
    props: true
  },
  {
    path: '/ticket',
    name: 'Ticket',
    component: Ticket,
  },
  {
    path: '/route',
    name: 'Route',
    component: Route,
  },
  {
    path: '/comment/:query',
    name: 'Comment',
    component: Comment,
    props: true
  }

]

const router = new VueRouter({
  routes
})

export default router
