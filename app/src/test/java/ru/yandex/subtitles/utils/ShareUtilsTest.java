package ru.yandex.subtitles.utils;

import android.content.Intent;
import android.net.Uri;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ShareUtilsTest extends TestCase {

    @Test
    public void testPrepareMailToIntent() throws Exception {
        final String[] addresses = {"test1@ya.ru", "test2@ya.ru", "test3@ya.ru"};
        final String subject = "test subject";
        final String body = "test message";

        Intent intent = ShareUtils.prepareMailToIntent(addresses, subject, body);

        assertThat(intent.getStringExtra(Intent.EXTRA_SUBJECT), equalTo(subject));
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT), equalTo(body));

        Uri data = intent.getData();
        assertThat(data.getScheme(), equalTo("mailto"));
        assertThat(data.getEncodedSchemeSpecificPart(), equalTo("test1@ya.ru,test2@ya.ru,test3@ya.ru?subject=test%20subject&body=test%20message"));

        assertArrayEquals(intent.getStringArrayExtra(Intent.EXTRA_EMAIL), addresses);
    }
}