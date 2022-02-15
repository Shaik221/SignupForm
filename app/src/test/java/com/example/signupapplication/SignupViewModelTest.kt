package com.example.signupapplication

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.signupapplication.model.User
import com.example.signupapplication.viewmodel.SignUpSharedViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupViewModelTest {

    val subject = SignUpSharedViewModel()
    private val application = mockk<Application>(relaxed = true)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

   @Test
    fun `invalid_if_email_is_not_matched_with_regex`() {
        every { application.getString(R.string.please_enter_valid_email) } returns "valid email required"

        val validations = subject.isRegexMatch("test.com")

       assertEquals( true, validations)
    }

    @Test
    fun `invalid_if_email_is_empty_or_null`() {
        every { application.getString(R.string.email_required) } returns "email required"

        val validations = subject.isRequired("")

        assertEquals( true, validations)
    }

    @Test
    fun `get_user_details_input_from_signup_form`() {
        val mockUser = mockk<User>(relaxed = true)

        every { mockUser.email } returns "test_updated.user@test.com"
        every { mockUser.firstName } returns "Test123"
        every { mockUser.webSite } returns "www.google.com"

        subject.setUserDetails(mockUser)

        assertNotNull(subject.getRegisteredUser())
        assertEquals("test_updated.user@test.com",subject.getRegisteredUser()!!.email)
        assertEquals("Test123",subject.getRegisteredUser()!!.firstName)
        assertEquals("www.google.com",subject.getRegisteredUser()!!.webSite)
    }

}