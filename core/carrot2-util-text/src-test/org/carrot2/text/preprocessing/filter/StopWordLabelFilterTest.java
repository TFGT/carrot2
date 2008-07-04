package org.carrot2.text.preprocessing.filter;

import org.carrot2.text.preprocessing.*;
import org.junit.Test;

/**
 * Test cases for {@link StopWordLabelFilter}.
 */
public class StopWordLabelFilterTest extends LabelFilterTestBase
{
    @Override
    protected void initializeFilters(LabelFilterProcessor filterProcessor)
    {
        filterProcessor.stopWordLabelFilter.enabled = true;
    }

    @Test
    public void testEmpty()
    {
        final int [] expectedLabelsFeatureIndex = new int [] {};

        check(expectedLabelsFeatureIndex);
    }

    @Test
    public void testNonStopWords()
    {
        createDocuments("aa . aa", "bb . bb");

        final int [] expectedLabelsFeatureIndex = new int []
        {
            0, 1
        };

        check(expectedLabelsFeatureIndex);
    }

    @Test
    public void testStopWords()
    {
        createDocuments("stop . stop", "bb . bb");

        final int [] expectedLabelsFeatureIndex = new int []
        {
            wordIndices.get("bb")
        };

        check(expectedLabelsFeatureIndex);
    }

    @Test
    public void testNonStopPhrases()
    {
        createDocuments("aa aa . aa aa", "bb bb . bb bb");

        final int [] expectedLabelsFeatureIndex = new int []
        {
            0, 1, 2, 3
        };

        check(expectedLabelsFeatureIndex);
    }

    @Test
    public void testStopPhrases()
    {
        createDocuments("aa stop aa . aa stop aa",
            "stop bb . stop bb . bb stop . bb stop");

        final int [] expectedLabelsFeatureIndex = new int []
        {
            0, 1, 7
        };

        check(expectedLabelsFeatureIndex);
    }
}
