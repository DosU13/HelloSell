package kg.hello.hello_sell.data.network.model

import com.google.firebase.firestore.DocumentSnapshot

data class Some(val name: String)

fun DocumentSnapshot.toSome(): Some {
    return Some(getString("name")!!)
}